"use strict";
Object.defineProperty(exports, "__esModule", { value: true });
/**
 * @license
 * Copyright Google Inc. All Rights Reserved.
 *
 * Use of this source code is governed by an MIT-style license that can be
 * found in the LICENSE file at https://angular.io/license
 */
const webpack_sources_1 = require("webpack-sources");
// This matches a comment left by the build-optimizer that contains pure import paths
const importCommentRegex = /\/\*\* PURE_IMPORTS_START (\S+) PURE_IMPORTS_END \*\//mg;
function purifyReplacements(content) {
    const pureImportMatches = getMatches(content, importCommentRegex, 1)
        // Remove dots at the start of matches.
        // Older versions of Purify added dots for relative imports.
        .map(match => match.replace(/^\.+/, ''))
        .join('|');
    if (!pureImportMatches) {
        return [];
    }
    const inserts = [];
    /* Prefix safe imports with pure */
    const regex = new RegExp(`(_(${pureImportMatches})__(_default)? = )(__webpack_require__(\\.\\w)?\\(\\S+\\);)`, 'mg');
    let match;
    // tslint:disable-next-line:no-conditional-assignment
    while (match = regex.exec(content)) {
        inserts.push({
            pos: match.index + match[1].length,
            content: '/*@__PURE__*/',
        });
    }
    return inserts;
}
exports.purifyReplacements = purifyReplacements;
function purify(content) {
    const rawSource = new webpack_sources_1.RawSource(content);
    const replaceSource = new webpack_sources_1.ReplaceSource(rawSource, 'file.js');
    const inserts = purifyReplacements(content);
    inserts.forEach((insert) => {
        replaceSource.insert(insert.pos, insert.content);
    });
    return replaceSource.source();
}
exports.purify = purify;
function getMatches(str, regex, index) {
    let matches = [];
    let match;
    // tslint:disable-next-line:no-conditional-assignment
    while (match = regex.exec(str)) {
        matches = matches.concat(match[index].split(','));
    }
    return matches;
}
//# sourceMappingURL=data:application/json;base64,eyJ2ZXJzaW9uIjozLCJmaWxlIjoicHVyaWZ5LmpzIiwic291cmNlUm9vdCI6Ii4vIiwic291cmNlcyI6WyJwYWNrYWdlcy9hbmd1bGFyX2RldmtpdC9idWlsZF9vcHRpbWl6ZXIvc3JjL3B1cmlmeS9wdXJpZnkudHMiXSwibmFtZXMiOltdLCJtYXBwaW5ncyI6Ijs7QUFBQTs7Ozs7O0dBTUc7QUFDSCxxREFBMkQ7QUFHM0QscUZBQXFGO0FBQ3JGLE1BQU0sa0JBQWtCLEdBQUcseURBQXlELENBQUM7QUFRckYsU0FBZ0Isa0JBQWtCLENBQUMsT0FBZTtJQUVoRCxNQUFNLGlCQUFpQixHQUFHLFVBQVUsQ0FBQyxPQUFPLEVBQUUsa0JBQWtCLEVBQUUsQ0FBQyxDQUFDO1FBQ2xFLHVDQUF1QztRQUN2Qyw0REFBNEQ7U0FDM0QsR0FBRyxDQUFDLEtBQUssQ0FBQyxFQUFFLENBQUMsS0FBSyxDQUFDLE9BQU8sQ0FBQyxNQUFNLEVBQUUsRUFBRSxDQUFDLENBQUM7U0FDdkMsSUFBSSxDQUFDLEdBQUcsQ0FBQyxDQUFDO0lBRWIsSUFBSSxDQUFDLGlCQUFpQixFQUFFO1FBQ3RCLE9BQU8sRUFBRSxDQUFDO0tBQ1g7SUFFRCxNQUFNLE9BQU8sR0FBYSxFQUFFLENBQUM7SUFFN0IsbUNBQW1DO0lBQ25DLE1BQU0sS0FBSyxHQUFHLElBQUksTUFBTSxDQUN0QixNQUFNLGlCQUFpQiw2REFBNkQsRUFDcEYsSUFBSSxDQUNMLENBQUM7SUFFRixJQUFJLEtBQUssQ0FBQztJQUNWLHFEQUFxRDtJQUNyRCxPQUFPLEtBQUssR0FBRyxLQUFLLENBQUMsSUFBSSxDQUFDLE9BQU8sQ0FBQyxFQUFFO1FBQ2xDLE9BQU8sQ0FBQyxJQUFJLENBQUM7WUFDWCxHQUFHLEVBQUUsS0FBSyxDQUFDLEtBQUssR0FBRyxLQUFLLENBQUMsQ0FBQyxDQUFDLENBQUMsTUFBTTtZQUNsQyxPQUFPLEVBQUUsZUFBZTtTQUN6QixDQUFDLENBQUM7S0FDSjtJQUVELE9BQU8sT0FBTyxDQUFDO0FBQ2pCLENBQUM7QUE5QkQsZ0RBOEJDO0FBRUQsU0FBZ0IsTUFBTSxDQUFDLE9BQWU7SUFDcEMsTUFBTSxTQUFTLEdBQUcsSUFBSSwyQkFBUyxDQUFDLE9BQU8sQ0FBQyxDQUFDO0lBQ3pDLE1BQU0sYUFBYSxHQUFHLElBQUksK0JBQWEsQ0FBQyxTQUFTLEVBQUUsU0FBUyxDQUFDLENBQUM7SUFFOUQsTUFBTSxPQUFPLEdBQUcsa0JBQWtCLENBQUMsT0FBTyxDQUFDLENBQUM7SUFDNUMsT0FBTyxDQUFDLE9BQU8sQ0FBQyxDQUFDLE1BQU0sRUFBRSxFQUFFO1FBQ3pCLGFBQWEsQ0FBQyxNQUFNLENBQUMsTUFBTSxDQUFDLEdBQUcsRUFBRSxNQUFNLENBQUMsT0FBTyxDQUFDLENBQUM7SUFDbkQsQ0FBQyxDQUFDLENBQUM7SUFFSCxPQUFPLGFBQWEsQ0FBQyxNQUFNLEVBQUUsQ0FBQztBQUNoQyxDQUFDO0FBVkQsd0JBVUM7QUFFRCxTQUFTLFVBQVUsQ0FBQyxHQUFXLEVBQUUsS0FBYSxFQUFFLEtBQWE7SUFDM0QsSUFBSSxPQUFPLEdBQWEsRUFBRSxDQUFDO0lBQzNCLElBQUksS0FBSyxDQUFDO0lBQ1YscURBQXFEO0lBQ3JELE9BQU8sS0FBSyxHQUFHLEtBQUssQ0FBQyxJQUFJLENBQUMsR0FBRyxDQUFDLEVBQUU7UUFDOUIsT0FBTyxHQUFHLE9BQU8sQ0FBQyxNQUFNLENBQUMsS0FBSyxDQUFDLEtBQUssQ0FBQyxDQUFDLEtBQUssQ0FBQyxHQUFHLENBQUMsQ0FBQyxDQUFDO0tBQ25EO0lBRUQsT0FBTyxPQUFPLENBQUM7QUFDakIsQ0FBQyIsInNvdXJjZXNDb250ZW50IjpbIi8qKlxuICogQGxpY2Vuc2VcbiAqIENvcHlyaWdodCBHb29nbGUgSW5jLiBBbGwgUmlnaHRzIFJlc2VydmVkLlxuICpcbiAqIFVzZSBvZiB0aGlzIHNvdXJjZSBjb2RlIGlzIGdvdmVybmVkIGJ5IGFuIE1JVC1zdHlsZSBsaWNlbnNlIHRoYXQgY2FuIGJlXG4gKiBmb3VuZCBpbiB0aGUgTElDRU5TRSBmaWxlIGF0IGh0dHBzOi8vYW5ndWxhci5pby9saWNlbnNlXG4gKi9cbmltcG9ydCB7IFJhd1NvdXJjZSwgUmVwbGFjZVNvdXJjZSB9IGZyb20gJ3dlYnBhY2stc291cmNlcyc7XG5cblxuLy8gVGhpcyBtYXRjaGVzIGEgY29tbWVudCBsZWZ0IGJ5IHRoZSBidWlsZC1vcHRpbWl6ZXIgdGhhdCBjb250YWlucyBwdXJlIGltcG9ydCBwYXRoc1xuY29uc3QgaW1wb3J0Q29tbWVudFJlZ2V4ID0gL1xcL1xcKlxcKiBQVVJFX0lNUE9SVFNfU1RBUlQgKFxcUyspIFBVUkVfSU1QT1JUU19FTkQgXFwqXFwvL21nO1xuXG4vLyBJbnNlcnRpb24gYXJlIG1lYW50IHRvIGJlIHVzZWQgd2l0aCBXZWJwYWNrJ3MgUmVwbGFjZVNvdXJjZS5cbmV4cG9ydCBpbnRlcmZhY2UgSW5zZXJ0IHtcbiAgcG9zOiBudW1iZXI7XG4gIGNvbnRlbnQ6IHN0cmluZztcbn1cblxuZXhwb3J0IGZ1bmN0aW9uIHB1cmlmeVJlcGxhY2VtZW50cyhjb250ZW50OiBzdHJpbmcpIHtcblxuICBjb25zdCBwdXJlSW1wb3J0TWF0Y2hlcyA9IGdldE1hdGNoZXMoY29udGVudCwgaW1wb3J0Q29tbWVudFJlZ2V4LCAxKVxuICAgIC8vIFJlbW92ZSBkb3RzIGF0IHRoZSBzdGFydCBvZiBtYXRjaGVzLlxuICAgIC8vIE9sZGVyIHZlcnNpb25zIG9mIFB1cmlmeSBhZGRlZCBkb3RzIGZvciByZWxhdGl2ZSBpbXBvcnRzLlxuICAgIC5tYXAobWF0Y2ggPT4gbWF0Y2gucmVwbGFjZSgvXlxcLisvLCAnJykpXG4gICAgLmpvaW4oJ3wnKTtcblxuICBpZiAoIXB1cmVJbXBvcnRNYXRjaGVzKSB7XG4gICAgcmV0dXJuIFtdO1xuICB9XG5cbiAgY29uc3QgaW5zZXJ0czogSW5zZXJ0W10gPSBbXTtcblxuICAvKiBQcmVmaXggc2FmZSBpbXBvcnRzIHdpdGggcHVyZSAqL1xuICBjb25zdCByZWdleCA9IG5ldyBSZWdFeHAoXG4gICAgYChfKCR7cHVyZUltcG9ydE1hdGNoZXN9KV9fKF9kZWZhdWx0KT8gPSApKF9fd2VicGFja19yZXF1aXJlX18oXFxcXC5cXFxcdyk/XFxcXChcXFxcUytcXFxcKTspYCxcbiAgICAnbWcnLFxuICApO1xuXG4gIGxldCBtYXRjaDtcbiAgLy8gdHNsaW50OmRpc2FibGUtbmV4dC1saW5lOm5vLWNvbmRpdGlvbmFsLWFzc2lnbm1lbnRcbiAgd2hpbGUgKG1hdGNoID0gcmVnZXguZXhlYyhjb250ZW50KSkge1xuICAgIGluc2VydHMucHVzaCh7XG4gICAgICBwb3M6IG1hdGNoLmluZGV4ICsgbWF0Y2hbMV0ubGVuZ3RoLFxuICAgICAgY29udGVudDogJy8qQF9fUFVSRV9fKi8nLFxuICAgIH0pO1xuICB9XG5cbiAgcmV0dXJuIGluc2VydHM7XG59XG5cbmV4cG9ydCBmdW5jdGlvbiBwdXJpZnkoY29udGVudDogc3RyaW5nKSB7XG4gIGNvbnN0IHJhd1NvdXJjZSA9IG5ldyBSYXdTb3VyY2UoY29udGVudCk7XG4gIGNvbnN0IHJlcGxhY2VTb3VyY2UgPSBuZXcgUmVwbGFjZVNvdXJjZShyYXdTb3VyY2UsICdmaWxlLmpzJyk7XG5cbiAgY29uc3QgaW5zZXJ0cyA9IHB1cmlmeVJlcGxhY2VtZW50cyhjb250ZW50KTtcbiAgaW5zZXJ0cy5mb3JFYWNoKChpbnNlcnQpID0+IHtcbiAgICByZXBsYWNlU291cmNlLmluc2VydChpbnNlcnQucG9zLCBpbnNlcnQuY29udGVudCk7XG4gIH0pO1xuXG4gIHJldHVybiByZXBsYWNlU291cmNlLnNvdXJjZSgpO1xufVxuXG5mdW5jdGlvbiBnZXRNYXRjaGVzKHN0cjogc3RyaW5nLCByZWdleDogUmVnRXhwLCBpbmRleDogbnVtYmVyKSB7XG4gIGxldCBtYXRjaGVzOiBzdHJpbmdbXSA9IFtdO1xuICBsZXQgbWF0Y2g7XG4gIC8vIHRzbGludDpkaXNhYmxlLW5leHQtbGluZTpuby1jb25kaXRpb25hbC1hc3NpZ25tZW50XG4gIHdoaWxlIChtYXRjaCA9IHJlZ2V4LmV4ZWMoc3RyKSkge1xuICAgIG1hdGNoZXMgPSBtYXRjaGVzLmNvbmNhdChtYXRjaFtpbmRleF0uc3BsaXQoJywnKSk7XG4gIH1cblxuICByZXR1cm4gbWF0Y2hlcztcbn1cbiJdfQ==