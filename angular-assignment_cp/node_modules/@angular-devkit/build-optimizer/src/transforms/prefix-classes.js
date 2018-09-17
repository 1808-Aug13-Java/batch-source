"use strict";
Object.defineProperty(exports, "__esModule", { value: true });
/**
 * @license
 * Copyright Google Inc. All Rights Reserved.
 *
 * Use of this source code is governed by an MIT-style license that can be
 * found in the LICENSE file at https://angular.io/license
 */
const ts = require("typescript");
function testPrefixClasses(content) {
    const exportVarSetter = /(?:export )?(?:var|const)\s+(\S+)\s*=\s*/;
    const multiLineComment = /\s*(?:\/\*[\s\S]*?\*\/)?\s*/;
    const newLine = /\s*\r?\n\s*/;
    const regexes = [
        [
            /^/,
            exportVarSetter, multiLineComment,
            /\(/, multiLineComment,
            /\s*function \(\) {/, newLine,
            multiLineComment,
            /function \1\([^\)]*\) \{/, newLine,
        ],
        [
            /^/,
            exportVarSetter, multiLineComment,
            /\(/, multiLineComment,
            /\s*function \(_super\) {/, newLine,
            /\w*\.?__extends\(\w+, _super\);/,
        ],
    ].map(arr => new RegExp(arr.map(x => x.source).join(''), 'm'));
    return regexes.some((regex) => regex.test(content));
}
exports.testPrefixClasses = testPrefixClasses;
const superParameterName = '_super';
const extendsHelperName = '__extends';
function getPrefixClassesTransformer() {
    return (context) => {
        const transformer = (sf) => {
            const pureFunctionComment = '@__PURE__';
            const visitor = (node) => {
                // Add pure comment to downleveled classes.
                if (ts.isVariableStatement(node) && isDownleveledClass(node)) {
                    const varDecl = node.declarationList.declarations[0];
                    const varInitializer = varDecl.initializer;
                    // Update node with the pure comment before the variable declaration initializer.
                    const newNode = ts.updateVariableStatement(node, node.modifiers, ts.updateVariableDeclarationList(node.declarationList, [
                        ts.updateVariableDeclaration(varDecl, varDecl.name, varDecl.type, ts.addSyntheticLeadingComment(varInitializer, ts.SyntaxKind.MultiLineCommentTrivia, pureFunctionComment, false)),
                    ]));
                    // Replace node with modified one.
                    return ts.visitEachChild(newNode, visitor, context);
                }
                // Otherwise return node as is.
                return ts.visitEachChild(node, visitor, context);
            };
            return ts.visitEachChild(sf, visitor, context);
        };
        return transformer;
    };
}
exports.getPrefixClassesTransformer = getPrefixClassesTransformer;
// Determine if a node matched the structure of a downleveled TS class.
function isDownleveledClass(node) {
    if (!ts.isVariableStatement(node)) {
        return false;
    }
    if (node.declarationList.declarations.length !== 1) {
        return false;
    }
    const variableDeclaration = node.declarationList.declarations[0];
    if (!ts.isIdentifier(variableDeclaration.name)
        || !variableDeclaration.initializer) {
        return false;
    }
    let potentialClass = variableDeclaration.initializer;
    // TS 2.3 has an unwrapped class IIFE
    // TS 2.4 uses a function expression wrapper
    // TS 2.5 uses an arrow function wrapper
    if (ts.isParenthesizedExpression(potentialClass)) {
        potentialClass = potentialClass.expression;
    }
    if (!ts.isCallExpression(potentialClass) || potentialClass.arguments.length > 1) {
        return false;
    }
    let wrapperBody;
    if (ts.isFunctionExpression(potentialClass.expression)) {
        wrapperBody = potentialClass.expression.body;
    }
    else if (ts.isArrowFunction(potentialClass.expression)
        && ts.isBlock(potentialClass.expression.body)) {
        wrapperBody = potentialClass.expression.body;
    }
    else {
        return false;
    }
    if (wrapperBody.statements.length === 0) {
        return false;
    }
    const functionExpression = potentialClass.expression;
    const functionStatements = wrapperBody.statements;
    // need a minimum of two for a function declaration and return statement
    if (functionStatements.length < 2) {
        return false;
    }
    // The variable name should be the class name.
    const className = variableDeclaration.name.text;
    const firstStatement = functionStatements[0];
    // find return statement - may not be last statement
    let returnStatement;
    for (let i = functionStatements.length - 1; i > 0; i--) {
        if (ts.isReturnStatement(functionStatements[i])) {
            returnStatement = functionStatements[i];
            break;
        }
    }
    if (returnStatement == undefined
        || returnStatement.expression == undefined
        || !ts.isIdentifier(returnStatement.expression)) {
        return false;
    }
    if (functionExpression.parameters.length === 0) {
        // potential non-extended class or wrapped es2015 class
        return (ts.isFunctionDeclaration(firstStatement) || ts.isClassDeclaration(firstStatement))
            && firstStatement.name !== undefined
            && firstStatement.name.text === className
            && returnStatement.expression.text === firstStatement.name.text;
    }
    else if (functionExpression.parameters.length !== 1) {
        return false;
    }
    // Potential extended class
    const functionParameter = functionExpression.parameters[0];
    if (!ts.isIdentifier(functionParameter.name)
        || functionParameter.name.text !== superParameterName) {
        return false;
    }
    if (functionStatements.length < 3 || !ts.isExpressionStatement(firstStatement)) {
        return false;
    }
    if (!ts.isCallExpression(firstStatement.expression)) {
        return false;
    }
    const extendCallExpression = firstStatement.expression;
    let functionName;
    if (ts.isIdentifier(extendCallExpression.expression)) {
        functionName = extendCallExpression.expression.text;
    }
    else if (ts.isPropertyAccessExpression(extendCallExpression.expression)) {
        functionName = extendCallExpression.expression.name.text;
    }
    if (!functionName || !functionName.endsWith(extendsHelperName)) {
        return false;
    }
    if (extendCallExpression.arguments.length === 0) {
        return false;
    }
    const lastArgument = extendCallExpression.arguments[extendCallExpression.arguments.length - 1];
    if (!ts.isIdentifier(lastArgument) || lastArgument.text !== functionParameter.name.text) {
        return false;
    }
    const secondStatement = functionStatements[1];
    return ts.isFunctionDeclaration(secondStatement)
        && secondStatement.name !== undefined
        && className.endsWith(secondStatement.name.text)
        && returnStatement.expression.text === secondStatement.name.text;
}
//# sourceMappingURL=data:application/json;base64,eyJ2ZXJzaW9uIjozLCJmaWxlIjoicHJlZml4LWNsYXNzZXMuanMiLCJzb3VyY2VSb290IjoiLi8iLCJzb3VyY2VzIjpbInBhY2thZ2VzL2FuZ3VsYXJfZGV2a2l0L2J1aWxkX29wdGltaXplci9zcmMvdHJhbnNmb3Jtcy9wcmVmaXgtY2xhc3Nlcy50cyJdLCJuYW1lcyI6W10sIm1hcHBpbmdzIjoiOztBQUFBOzs7Ozs7R0FNRztBQUNILGlDQUFpQztBQUdqQyxTQUFnQixpQkFBaUIsQ0FBQyxPQUFlO0lBQy9DLE1BQU0sZUFBZSxHQUFHLDBDQUEwQyxDQUFDO0lBQ25FLE1BQU0sZ0JBQWdCLEdBQUcsNkJBQTZCLENBQUM7SUFDdkQsTUFBTSxPQUFPLEdBQUcsYUFBYSxDQUFDO0lBRTlCLE1BQU0sT0FBTyxHQUFHO1FBQ2Q7WUFDRSxHQUFHO1lBQ0gsZUFBZSxFQUFFLGdCQUFnQjtZQUNqQyxJQUFJLEVBQUUsZ0JBQWdCO1lBQ3RCLG9CQUFvQixFQUFFLE9BQU87WUFDN0IsZ0JBQWdCO1lBQ2hCLDBCQUEwQixFQUFFLE9BQU87U0FDcEM7UUFDRDtZQUNFLEdBQUc7WUFDSCxlQUFlLEVBQUUsZ0JBQWdCO1lBQ2pDLElBQUksRUFBRSxnQkFBZ0I7WUFDdEIsMEJBQTBCLEVBQUUsT0FBTztZQUNuQyxpQ0FBaUM7U0FDbEM7S0FDRixDQUFDLEdBQUcsQ0FBQyxHQUFHLENBQUMsRUFBRSxDQUFDLElBQUksTUFBTSxDQUFDLEdBQUcsQ0FBQyxHQUFHLENBQUMsQ0FBQyxDQUFDLEVBQUUsQ0FBQyxDQUFDLENBQUMsTUFBTSxDQUFDLENBQUMsSUFBSSxDQUFDLEVBQUUsQ0FBQyxFQUFFLEdBQUcsQ0FBQyxDQUFDLENBQUM7SUFFL0QsT0FBTyxPQUFPLENBQUMsSUFBSSxDQUFDLENBQUMsS0FBSyxFQUFFLEVBQUUsQ0FBQyxLQUFLLENBQUMsSUFBSSxDQUFDLE9BQU8sQ0FBQyxDQUFDLENBQUM7QUFDdEQsQ0FBQztBQXhCRCw4Q0F3QkM7QUFFRCxNQUFNLGtCQUFrQixHQUFHLFFBQVEsQ0FBQztBQUNwQyxNQUFN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QUksQ0FBQztBQUMxRSxDQUFDIiwic291cmNlc0NvbnRlbnQiOlsiLyoqXG4gKiBAbGljZW5zZVxuICogQ29weXJpZ2h0IEdvb2dsZSBJbmMuIEFsbCBSaWdodHMgUmVzZXJ2ZWQuXG4gKlxuICogVXNlIG9mIHRoaXMgc291cmNlIGNvZGUgaXMgZ292ZXJuZWQgYnkgYW4gTUlULXN0eWxlIGxpY2Vuc2UgdGhhdCBjYW4gYmVcbiAqIGZvdW5kIGluIHRoZSBMSUNFTlNFIGZpbGUgYXQgaHR0cHM6Ly9hbmd1bGFyLmlvL2xpY2Vuc2VcbiAqL1xuaW1wb3J0ICogYXMgdHMgZnJvbSAndHlwZXNjcmlwdCc7XG5cblxuZXhwb3J0IGZ1bmN0aW9uIHRlc3RQcmVmaXhDbGFzc2VzKGNvbnRlbnQ6IHN0cmluZykge1xuICBjb25zdCBleHBvcnRWYXJTZXR0ZXIgPSAvKD86ZXhwb3J0ICk/KD86dmFyfGNvbnN0KVxccysoXFxTKylcXHMqPVxccyovO1xuICBjb25zdCBtdWx0aUxpbmVDb21tZW50ID0gL1xccyooPzpcXC9cXCpbXFxzXFxTXSo/XFwqXFwvKT9cXHMqLztcbiAgY29uc3QgbmV3TGluZSA9IC9cXHMqXFxyP1xcblxccyovO1xuXG4gIGNvbnN0IHJlZ2V4ZXMgPSBbXG4gICAgW1xuICAgICAgL14vLFxuICAgICAgZXhwb3J0VmFyU2V0dGVyLCBtdWx0aUxpbmVDb21tZW50LFxuICAgICAgL1xcKC8sIG11bHRpTGluZUNvbW1lbnQsXG4gICAgICAvXFxzKmZ1bmN0aW9uIFxcKFxcKSB7LywgbmV3TGluZSxcbiAgICAgIG11bHRpTGluZUNvbW1lbnQsXG4gICAgICAvZnVuY3Rpb24gXFwxXFwoW15cXCldKlxcKSBcXHsvLCBuZXdMaW5lLFxuICAgIF0sXG4gICAgW1xuICAgICAgL14vLFxuICAgICAgZXhwb3J0VmFyU2V0dGVyLCBtdWx0aUxpbmVDb21tZW50LFxuICAgICAgL1xcKC8sIG11bHRpTGluZUNvbW1lbnQsXG4gICAgICAvXFxzKmZ1bmN0aW9uIFxcKF9zdXBlclxcKSB7LywgbmV3TGluZSxcbiAgICAgIC9cXHcqXFwuP19fZXh0ZW5kc1xcKFxcdyssIF9zdXBlclxcKTsvLFxuICAgIF0sXG4gIF0ubWFwKGFyciA9PiBuZXcgUmVnRXhwKGFyci5tYXAoeCA9PiB4LnNvdXJjZSkuam9pbignJyksICdtJykpO1xuXG4gIHJldHVybiByZWdleGVzLnNvbWUoKHJlZ2V4KSA9PiByZWdleC50ZXN0KGNvbnRlbnQpKTtcbn1cblxuY29uc3Qgc3VwZXJQYXJhbWV0ZXJOYW1lID0gJ19zdXBlcic7XG5jb25zdCBleHRlbmRzSGVscGVyTmFtZSA9ICdfX2V4dGVuZHMnO1xuXG5leHBvcnQgZnVuY3Rpb24gZ2V0UHJlZml4Q2xhc3Nlc1RyYW5zZm9ybWVyKCk6IHRzLlRyYW5zZm9ybWVyRmFjdG9yeTx0cy5Tb3VyY2VGaWxlPiB7XG4gIHJldHVybiAoY29udGV4dDogdHMuVHJhbnNmb3JtYXRpb25Db250ZXh0KTogdHMuVHJhbnNmb3JtZXI8dHMuU291cmNlRmlsZT4gPT4ge1xuICAgIGNvbnN0IHRyYW5zZm9ybWVyOiB0cy5UcmFuc2Zvcm1lcjx0cy5Tb3VyY2VGaWxlPiA9IChzZjogdHMuU291cmNlRmlsZSkgPT4ge1xuXG4gICAgICBjb25zdCBwdXJlRnVuY3Rpb25Db21tZW50ID0gJ0BfX1BVUkVfXyc7XG5cbiAgICAgIGNvbnN0IHZpc2l0b3I6IHRzLlZpc2l0b3IgPSAobm9kZTogdHMuTm9kZSk6IHRzLlZpc2l0UmVzdWx0PHRzLk5vZGU+ID0+IHtcblxuICAgICAgICAvLyBBZGQgcHVyZSBjb21tZW50IHRvIGRvd25sZXZlbGVkIGNsYXNzZXMuXG4gICAgICAgIGlmICh0cy5pc1ZhcmlhYmxlU3RhdGVtZW50KG5vZGUpICYmIGlzRG93bmxldmVsZWRDbGFzcyhub2RlKSkge1xuICAgICAgICAgIGNvbnN0IHZhckRlY2wgPSBub2RlLmRlY2xhcmF0aW9uTGlzdC5kZWNsYXJhdGlvbnNbMF07XG4gICAgICAgICAgY29uc3QgdmFySW5pdGlhbGl6ZXIgPSB2YXJEZWNsLmluaXRpYWxpemVyIGFzIHRzLkV4cHJlc3Npb247XG5cbiAgICAgICAgICAvLyBVcGRhdGUgbm9kZSB3aXRoIHRoZSBwdXJlIGNvbW1lbnQgYmVmb3JlIHRoZSB2YXJpYWJsZSBkZWNsYXJhdGlvbiBpbml0aWFsaXplci5cbiAgICAgICAgICBjb25zdCBuZXdOb2RlID0gdHMudXBkYXRlVmFyaWFibGVTdGF0ZW1lbnQoXG4gICAgICAgICAgICBub2RlLFxuICAgICAgICAgICAgbm9kZS5tb2RpZmllcnMsXG4gICAgICAgICAgICB0cy51cGRhdGVWYXJpYWJsZURlY2xhcmF0aW9uTGlzdChcbiAgICAgICAgICAgICAgbm9kZS5kZWNsYXJhdGlvbkxpc3QsXG4gICAgICAgICAgICAgIFtcbiAgICAgICAgICAgICAgICB0cy51cGRhdGVWYXJpYWJsZURlY2xhcmF0aW9uKFxuICAgICAgICAgICAgICAgICAgdmFyRGVjbCxcbiAgICAgICAgICAgICAgICAgIHZhckRlY2wubmFtZSxcbiAgICAgICAgICAgICAgICAgIHZhckRlY2wudHlwZSxcbiAgICAgICAgICAgICAgICAgIHRzLmFkZFN5bnRoZXRpY0xlYWRpbmdDb21tZW50KFxuICAgICAgICAgICAgICAgICAgICB2YXJJbml0aWFsaXplcixcbiAgICAgICAgICAgICAgICAgICAgdHMuU3ludGF4S2luZC5NdWx0aUxpbmVDb21tZW50VHJpdmlhLFxuICAgICAgICAgICAgICAgICAgICBwdXJlRnVuY3Rpb25Db21tZW50LFxuICAgICAgICAgICAgICAgICAgICBmYWxzZSxcbiAgICAgICAgICAgICAgICAgICksXG4gICAgICAgICAgICAgICAgKSxcbiAgICAgICAgICAgICAgXSxcbiAgICAgICAgICAgICksXG4gICAgICAgICAgKTtcblxuICAgICAgICAgIC8vIFJlcGxhY2Ugbm9kZSB3aXRoIG1vZGlmaWVkIG9uZS5cbiAgICAgICAgICByZXR1cm4gdHMudmlzaXRFYWNoQ2hpbGQobmV3Tm9kZSwgdmlzaXRvciwgY29udGV4dCk7XG4gICAgICAgIH1cblxuICAgICAgICAvLyBPdGhlcndpc2UgcmV0dXJuIG5vZGUgYXMgaXMuXG4gICAgICAgIHJldHVybiB0cy52aXNpdEVhY2hDaGlsZChub2RlLCB2aXNpdG9yLCBjb250ZXh0KTtcbiAgICAgIH07XG5cbiAgICAgIHJldHVybiB0cy52aXNpdEVhY2hDaGlsZChzZiwgdmlzaXRvciwgY29udGV4dCk7XG4gICAgfTtcblxuICAgIHJldHVybiB0cmFuc2Zvcm1lcjtcbiAgfTtcbn1cblxuLy8gRGV0ZXJtaW5lIGlmIGEgbm9kZSBtYXRjaGVkIHRoZSBzdHJ1Y3R1cmUgb2YgYSBkb3dubGV2ZWxlZCBUUyBjbGFzcy5cbmZ1bmN0aW9uIGlzRG93bmxldmVsZWRDbGFzcyhub2RlOiB0cy5Ob2RlKTogYm9vbGVhbiB7XG5cbiAgaWYgKCF0cy5pc1ZhcmlhYmxlU3RhdGVtZW50KG5vZGUpKSB7XG4gICAgcmV0dXJuIGZhbHNlO1xuICB9XG5cbiAgaWYgKG5vZGUuZGVjbGFyYXRpb25MaXN0LmRlY2xhcmF0aW9ucy5sZW5ndGggIT09IDEpIHtcbiAgICByZXR1cm4gZmFsc2U7XG4gIH1cblxuICBjb25zdCB2YXJpYWJsZURlY2xhcmF0aW9uID0gbm9kZS5kZWNsYXJhdGlvbkxpc3QuZGVjbGFyYXRpb25zWzBdO1xuXG4gIGlmICghdHMuaXNJZGVudGlmaWVyKHZhcmlhYmxlRGVjbGFyYXRpb24ubmFtZSlcbiAgICAgIHx8ICF2YXJpYWJsZURlY2xhcmF0aW9uLmluaXRpYWxpemVyKSB7XG4gICAgcmV0dXJuIGZhbHNlO1xuICB9XG5cbiAgbGV0IHBvdGVudGlhbENsYXNzID0gdmFyaWFibGVEZWNsYXJhdGlvbi5pbml0aWFsaXplcjtcblxuICAvLyBUUyAyLjMgaGFzIGFuIHVud3JhcHBlZCBjbGFzcyBJSUZFXG4gIC8vIFRTIDIuNCB1c2VzIGEgZnVuY3Rpb24gZXhwcmVzc2lvbiB3cmFwcGVyXG4gIC8vIFRTIDIuNSB1c2VzIGFuIGFycm93IGZ1bmN0aW9uIHdyYXBwZXJcbiAgaWYgKHRzLmlzUGFyZW50aGVzaXplZEV4cHJlc3Npb24ocG90ZW50aWFsQ2xhc3MpKSB7XG4gICAgcG90ZW50aWFsQ2xhc3MgPSBwb3RlbnRpYWxDbGFzcy5leHByZXNzaW9uO1xuICB9XG5cbiAgaWYgKCF0cy5pc0NhbGxFeHByZXNzaW9uKHBvdGVudGlhbENsYXNzKSB8fCBwb3RlbnRpYWxDbGFzcy5hcmd1bWVudHMubGVuZ3RoID4gMSkge1xuICAgIHJldHVybiBmYWxzZTtcbiAgfVxuXG4gIGxldCB3cmFwcGVyQm9keTogdHMuQmxvY2s7XG4gIGlmICh0cy5pc0Z1bmN0aW9uRXhwcmVzc2lvbihwb3RlbnRpYWxDbGFzcy5leHByZXNzaW9uKSkge1xuICAgIHdyYXBwZXJCb2R5ID0gcG90ZW50aWFsQ2xhc3MuZXhwcmVzc2lvbi5ib2R5O1xuICB9IGVsc2UgaWYgKHRzLmlzQXJyb3dGdW5jdGlvbihwb3RlbnRpYWxDbGFzcy5leHByZXNzaW9uKVxuICAgICAgICAgICAgICYmIHRzLmlzQmxvY2socG90ZW50aWFsQ2xhc3MuZXhwcmVzc2lvbi5ib2R5KSkge1xuICAgIHdyYXBwZXJCb2R5ID0gcG90ZW50aWFsQ2xhc3MuZXhwcmVzc2lvbi5ib2R5O1xuICB9IGVsc2Uge1xuICAgIHJldHVybiBmYWxzZTtcbiAgfVxuXG4gIGlmICh3cmFwcGVyQm9keS5zdGF0ZW1lbnRzLmxlbmd0aCA9PT0gMCkge1xuICAgIHJldHVybiBmYWxzZTtcbiAgfVxuXG4gIGNvbnN0IGZ1bmN0aW9uRXhwcmVzc2lvbiA9IHBvdGVudGlhbENsYXNzLmV4cHJlc3Npb247XG4gIGNvbnN0IGZ1bmN0aW9uU3RhdGVtZW50cyA9IHdyYXBwZXJCb2R5LnN0YXRlbWVudHM7XG5cbiAgLy8gbmVlZCBhIG1pbmltdW0gb2YgdHdvIGZvciBhIGZ1bmN0aW9uIGRlY2xhcmF0aW9uIGFuZCByZXR1cm4gc3RhdGVtZW50XG4gIGlmIChmdW5jdGlvblN0YXRlbWVudHMubGVuZ3RoIDwgMikge1xuICAgIHJldHVybiBmYWxzZTtcbiAgfVxuXG4gIC8vIFRoZSB2YXJpYWJsZSBuYW1lIHNob3VsZCBiZSB0aGUgY2xhc3MgbmFtZS5cbiAgY29uc3QgY2xhc3NOYW1lID0gdmFyaWFibGVEZWNsYXJhdGlvbi5uYW1lLnRleHQ7XG5cbiAgY29uc3QgZmlyc3RTdGF0ZW1lbnQgPSBmdW5jdGlvblN0YXRlbWVudHNbMF07XG5cbiAgLy8gZmluZCByZXR1cm4gc3RhdGVtZW50IC0gbWF5IG5vdCBiZSBsYXN0IHN0YXRlbWVudFxuICBsZXQgcmV0dXJuU3RhdGVtZW50OiB0cy5SZXR1cm5TdGF0ZW1lbnQgfCB1bmRlZmluZWQ7XG4gIGZvciAobGV0IGkgPSBmdW5jdGlvblN0YXRlbWVudHMubGVuZ3RoIC0gMTsgaSA+IDA7IGktLSkge1xuICAgIGlmICh0cy5pc1JldHVyblN0YXRlbWVudChmdW5jdGlvblN0YXRlbWVudHNbaV0pKSB7XG4gICAgICByZXR1cm5TdGF0ZW1lbnQgPSBmdW5jdGlvblN0YXRlbWVudHNbaV0gYXMgdHMuUmV0dXJuU3RhdGVtZW50O1xuICAgICAgYnJlYWs7XG4gICAgfVxuICB9XG5cbiAgaWYgKHJldHVyblN0YXRlbWVudCA9PSB1bmRlZmluZWRcbiAgICAgIHx8IHJldHVyblN0YXRlbWVudC5leHByZXNzaW9uID09IHVuZGVmaW5lZFxuICAgICAgfHwgIXRzLmlzSWRlbnRpZmllcihyZXR1cm5TdGF0ZW1lbnQuZXhwcmVzc2lvbikpIHtcbiAgICByZXR1cm4gZmFsc2U7XG4gIH1cblxuICBpZiAoZnVuY3Rpb25FeHByZXNzaW9uLnBhcmFtZXRlcnMubGVuZ3RoID09PSAwKSB7XG4gICAgLy8gcG90ZW50aWFsIG5vbi1leHRlbmRlZCBjbGFzcyBvciB3cmFwcGVkIGVzMjAxNSBjbGFzc1xuICAgIHJldHVybiAodHMuaXNGdW5jdGlvbkRlY2xhcmF0aW9uKGZpcnN0U3RhdGVtZW50KSB8fCB0cy5pc0NsYXNzRGVjbGFyYXRpb24oZmlyc3RTdGF0ZW1lbnQpKVxuICAgICAgICAgICAmJiBmaXJzdFN0YXRlbWVudC5uYW1lICE9PSB1bmRlZmluZWRcbiAgICAgICAgICAgJiYgZmlyc3RTdGF0ZW1lbnQubmFtZS50ZXh0ID09PSBjbGFzc05hbWVcbiAgICAgICAgICAgJiYgcmV0dXJuU3RhdGVtZW50LmV4cHJlc3Npb24udGV4dCA9PT0gZmlyc3RTdGF0ZW1lbnQubmFtZS50ZXh0O1xuICB9IGVsc2UgaWYgKGZ1bmN0aW9uRXhwcmVzc2lvbi5wYXJhbWV0ZXJzLmxlbmd0aCAhPT0gMSkge1xuICAgIHJldHVybiBmYWxzZTtcbiAgfVxuXG4gIC8vIFBvdGVudGlhbCBleHRlbmRlZCBjbGFzc1xuXG4gIGNvbnN0IGZ1bmN0aW9uUGFyYW1ldGVyID0gZnVuY3Rpb25FeHByZXNzaW9uLnBhcmFtZXRlcnNbMF07XG5cbiAgaWYgKCF0cy5pc0lkZW50aWZpZXIoZnVuY3Rpb25QYXJhbWV0ZXIubmFtZSlcbiAgICAgIHx8IGZ1bmN0aW9uUGFyYW1ldGVyLm5hbWUudGV4dCAhPT0gc3VwZXJQYXJhbWV0ZXJOYW1lKSB7XG4gICAgcmV0dXJuIGZhbHNlO1xuICB9XG5cbiAgaWYgKGZ1bmN0aW9uU3RhdGVtZW50cy5sZW5ndGggPCAzIHx8ICF0cy5pc0V4cHJlc3Npb25TdGF0ZW1lbnQoZmlyc3RTdGF0ZW1lbnQpKSB7XG4gICAgcmV0dXJuIGZhbHNlO1xuICB9XG5cbiAgaWYgKCF0cy5pc0NhbGxFeHByZXNzaW9uKGZpcnN0U3RhdGVtZW50LmV4cHJlc3Npb24pKSB7XG4gICAgcmV0dXJuIGZhbHNlO1xuICB9XG5cbiAgY29uc3QgZXh0ZW5kQ2FsbEV4cHJlc3Npb24gPSBmaXJzdFN0YXRlbWVudC5leHByZXNzaW9uO1xuXG4gIGxldCBmdW5jdGlvbk5hbWU7XG4gIGlmICh0cy5pc0lkZW50aWZpZXIoZXh0ZW5kQ2FsbEV4cHJlc3Npb24uZXhwcmVzc2lvbikpIHtcbiAgICBmdW5jdGlvbk5hbWUgPSBleHRlbmRDYWxsRXhwcmVzc2lvbi5leHByZXNzaW9uLnRleHQ7XG4gIH0gZWxzZSBpZiAodHMuaXNQcm9wZXJ0eUFjY2Vzc0V4cHJlc3Npb24oZXh0ZW5kQ2FsbEV4cHJlc3Npb24uZXhwcmVzc2lvbikpIHtcbiAgICBmdW5jdGlvbk5hbWUgPSBleHRlbmRDYWxsRXhwcmVzc2lvbi5leHByZXNzaW9uLm5hbWUudGV4dDtcbiAgfVxuXG4gIGlmICghZnVuY3Rpb25OYW1lIHx8ICFmdW5jdGlvbk5hbWUuZW5kc1dpdGgoZXh0ZW5kc0hlbHBlck5hbWUpKSB7XG4gICAgcmV0dXJuIGZhbHNlO1xuICB9XG5cbiAgaWYgKGV4dGVuZENhbGxFeHByZXNzaW9uLmFyZ3VtZW50cy5sZW5ndGggPT09IDApIHtcbiAgICByZXR1cm4gZmFsc2U7XG4gIH1cblxuICBjb25zdCBsYXN0QXJndW1lbnQgPSBleHRlbmRDYWxsRXhwcmVzc2lvbi5hcmd1bWVudHNbZXh0ZW5kQ2FsbEV4cHJlc3Npb24uYXJndW1lbnRzLmxlbmd0aCAtIDFdO1xuXG4gIGlmICghdHMuaXNJZGVudGlmaWVyKGxhc3RBcmd1bWVudCkgfHwgbGFzdEFyZ3VtZW50LnRleHQgIT09IGZ1bmN0aW9uUGFyYW1ldGVyLm5hbWUudGV4dCkge1xuICAgIHJldHVybiBmYWxzZTtcbiAgfVxuXG4gIGNvbnN0IHNlY29uZFN0YXRlbWVudCA9IGZ1bmN0aW9uU3RhdGVtZW50c1sxXTtcblxuICByZXR1cm4gdHMuaXNGdW5jdGlvbkRlY2xhcmF0aW9uKHNlY29uZFN0YXRlbWVudClcbiAgICAgICAgICYmIHNlY29uZFN0YXRlbWVudC5uYW1lICE9PSB1bmRlZmluZWRcbiAgICAgICAgICYmIGNsYXNzTmFtZS5lbmRzV2l0aChzZWNvbmRTdGF0ZW1lbnQubmFtZS50ZXh0KVxuICAgICAgICAgJiYgcmV0dXJuU3RhdGVtZW50LmV4cHJlc3Npb24udGV4dCA9PT0gc2Vjb25kU3RhdGVtZW50Lm5hbWUudGV4dDtcbn1cbiJdfQ==