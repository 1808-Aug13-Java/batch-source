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
// Find all nodes from the AST in the subtree of node of SyntaxKind kind.
function collectDeepNodes(node, kind) {
    const nodes = [];
    const helper = (child) => {
        if (child.kind === kind) {
            nodes.push(child);
        }
        ts.forEachChild(child, helper);
    };
    ts.forEachChild(node, helper);
    return nodes;
}
exports.collectDeepNodes = collectDeepNodes;
function drilldownNodes(startingNode, path) {
    let currentNode = startingNode;
    for (const segment of path) {
        if (segment.prop) {
            // ts.Node has no index signature, so we need to cast it as any.
            const tempNode = currentNode[segment.prop];
            if (!tempNode || typeof tempNode != 'object' || currentNode.kind !== segment.kind) {
                return null;
            }
            // tslint:disable-next-line:no-any
            currentNode = tempNode;
        }
    }
    return currentNode;
}
exports.drilldownNodes = drilldownNodes;
//# sourceMappingURL=data:application/json;base64,eyJ2ZXJzaW9uIjozLCJmaWxlIjoiYXN0LXV0aWxzLmpzIiwic291cmNlUm9vdCI6Ii4vIiwic291cmNlcyI6WyJwYWNrYWdlcy9hbmd1bGFyX2RldmtpdC9idWlsZF9vcHRpbWl6ZXIvc3JjL2hlbHBlcnMvYXN0LXV0aWxzLnRzIl0sIm5hbWVzIjpbXSwibWFwcGluZ3MiOiI7O0FBQUE7Ozs7OztHQU1HO0FBQ0gsaUNBQWlDO0FBRWpDLHlFQUF5RTtBQUN6RSxTQUFnQixnQkFBZ0IsQ0FBb0IsSUFBYSxFQUFFLElBQW1CO0lBQ3BGLE1BQU0sS0FBSyxHQUFRLEVBQUUsQ0FBQztJQUN0QixNQUFNLE1BQU0sR0FBRyxDQUFDLEtBQWMsRUFBRSxFQUFFO1FBQ2hDLElBQUksS0FBSyxDQUFDLElBQUksS0FBSyxJQUFJLEVBQUU7WUFDdkIsS0FBSyxDQUFDLElBQUksQ0FBQyxLQUFVLENBQUMsQ0FBQztTQUN4QjtRQUNELEVBQUUsQ0FBQyxZQUFZLENBQUMsS0FBSyxFQUFFLE1BQU0sQ0FBQyxDQUFDO0lBQ2pDLENBQUMsQ0FBQztJQUNGLEVBQUUsQ0FBQyxZQUFZLENBQUMsSUFBSSxFQUFFLE1BQU0sQ0FBQyxDQUFDO0lBRTlCLE9BQU8sS0FBSyxDQUFDO0FBQ2YsQ0FBQztBQVhELDRDQVdDO0FBRUQsU0FBZ0IsY0FBYyxDQUM1QixZQUFlLEVBQ2YsSUFBOEM7SUFFOUMsSUFBSSxXQUFXLEdBQU0sWUFBWSxDQUFDO0lBQ2xDLEtBQUssTUFBTSxPQUFPLElBQUksSUFBSSxFQUFFO1FBQzFCLElBQUksT0FBTyxDQUFDLElBQUksRUFBRTtZQUNoQixnRUFBZ0U7WUFDaEUsTUFBTSxRQUFRLEdBQUcsV0FBVyxDQUFDLE9BQU8sQ0FBQyxJQUFJLENBQUMsQ0FBQztZQUMzQyxJQUFJLENBQUMsUUFBUSxJQUFJLE9BQU8sUUFBUSxJQUFJLFFBQVEsSUFBSSxXQUFXLENBQUMsSUFBSSxLQUFLLE9BQU8sQ0FBQyxJQUFJLEVBQUU7Z0JBQ2pGLE9BQU8sSUFBSSxDQUFDO2FBQ2I7WUFFRCxrQ0FBa0M7WUFDbEMsV0FBVyxHQUFHLFFBQW9CLENBQUM7U0FDcEM7S0FDRjtJQUVELE9BQU8sV0FBVyxDQUFDO0FBQ3JCLENBQUM7QUFuQkQsd0NBbUJDIiwic291cmNlc0NvbnRlbnQiOlsiLyoqXG4gKiBAbGljZW5zZVxuICogQ29weXJpZ2h0IEdvb2dsZSBJbmMuIEFsbCBSaWdodHMgUmVzZXJ2ZWQuXG4gKlxuICogVXNlIG9mIHRoaXMgc291cmNlIGNvZGUgaXMgZ292ZXJuZWQgYnkgYW4gTUlULXN0eWxlIGxpY2Vuc2UgdGhhdCBjYW4gYmVcbiAqIGZvdW5kIGluIHRoZSBMSUNFTlNFIGZpbGUgYXQgaHR0cHM6Ly9hbmd1bGFyLmlvL2xpY2Vuc2VcbiAqL1xuaW1wb3J0ICogYXMgdHMgZnJvbSAndHlwZXNjcmlwdCc7XG5cbi8vIEZpbmQgYWxsIG5vZGVzIGZyb20gdGhlIEFTVCBpbiB0aGUgc3VidHJlZSBvZiBub2RlIG9mIFN5bnRheEtpbmQga2luZC5cbmV4cG9ydCBmdW5jdGlvbiBjb2xsZWN0RGVlcE5vZGVzPFQgZXh0ZW5kcyB0cy5Ob2RlPihub2RlOiB0cy5Ob2RlLCBraW5kOiB0cy5TeW50YXhLaW5kKTogVFtdIHtcbiAgY29uc3Qgbm9kZXM6IFRbXSA9IFtdO1xuICBjb25zdCBoZWxwZXIgPSAoY2hpbGQ6IHRzLk5vZGUpID0+IHtcbiAgICBpZiAoY2hpbGQua2luZCA9PT0ga2luZCkge1xuICAgICAgbm9kZXMucHVzaChjaGlsZCBhcyBUKTtcbiAgICB9XG4gICAgdHMuZm9yRWFjaENoaWxkKGNoaWxkLCBoZWxwZXIpO1xuICB9O1xuICB0cy5mb3JFYWNoQ2hpbGQobm9kZSwgaGVscGVyKTtcblxuICByZXR1cm4gbm9kZXM7XG59XG5cbmV4cG9ydCBmdW5jdGlvbiBkcmlsbGRvd25Ob2RlczxUIGV4dGVuZHMgdHMuTm9kZT4oXG4gIHN0YXJ0aW5nTm9kZTogVCxcbiAgcGF0aDogeyBwcm9wOiBrZXlvZiBULCBraW5kOiB0cy5TeW50YXhLaW5kIH1bXSxcbik6IFQgfCBudWxsIHtcbiAgbGV0IGN1cnJlbnROb2RlOiBUID0gc3RhcnRpbmdOb2RlO1xuICBmb3IgKGNvbnN0IHNlZ21lbnQgb2YgcGF0aCkge1xuICAgIGlmIChzZWdtZW50LnByb3ApIHtcbiAgICAgIC8vIHRzLk5vZGUgaGFzIG5vIGluZGV4IHNpZ25hdHVyZSwgc28gd2UgbmVlZCB0byBjYXN0IGl0IGFzIGFueS5cbiAgICAgIGNvbnN0IHRlbXBOb2RlID0gY3VycmVudE5vZGVbc2VnbWVudC5wcm9wXTtcbiAgICAgIGlmICghdGVtcE5vZGUgfHwgdHlwZW9mIHRlbXBOb2RlICE9ICdvYmplY3QnIHx8IGN1cnJlbnROb2RlLmtpbmQgIT09IHNlZ21lbnQua2luZCkge1xuICAgICAgICByZXR1cm4gbnVsbDtcbiAgICAgIH1cblxuICAgICAgLy8gdHNsaW50OmRpc2FibGUtbmV4dC1saW5lOm5vLWFueVxuICAgICAgY3VycmVudE5vZGUgPSB0ZW1wTm9kZSBhcyBhbnkgYXMgVDtcbiAgICB9XG4gIH1cblxuICByZXR1cm4gY3VycmVudE5vZGU7XG59XG4iXX0=