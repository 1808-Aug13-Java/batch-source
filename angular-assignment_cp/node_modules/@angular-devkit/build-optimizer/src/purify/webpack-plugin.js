"use strict";
Object.defineProperty(exports, "__esModule", { value: true });
const webpack_sources_1 = require("webpack-sources");
const purify_1 = require("./purify");
class PurifyPlugin {
    constructor() { }
    apply(compiler) {
        compiler.plugin('compilation', (compilation) => {
            // Webpack 4 provides the same functionality as this plugin and TS transformer
            compilation.warnings.push('PurifyPlugin is deprecated and will be removed in 0.7.0.');
            compilation.plugin('optimize-chunk-assets', (chunks, callback) => {
                chunks.forEach((chunk) => {
                    chunk.files
                        .filter((fileName) => fileName.endsWith('.js'))
                        .forEach((fileName) => {
                        const inserts = purify_1.purifyReplacements(compilation.assets[fileName].source());
                        if (inserts.length > 0) {
                            const replaceSource = new webpack_sources_1.ReplaceSource(compilation.assets[fileName], fileName);
                            inserts.forEach((insert) => {
                                replaceSource.insert(insert.pos, insert.content);
                            });
                            compilation.assets[fileName] = replaceSource;
                        }
                    });
                });
                callback();
            });
        });
    }
}
exports.PurifyPlugin = PurifyPlugin;
//# sourceMappingURL=data:application/json;base64,eyJ2ZXJzaW9uIjozLCJmaWxlIjoid2VicGFjay1wbHVnaW4uanMiLCJzb3VyY2VSb290IjoiLi8iLCJzb3VyY2VzIjpbInBhY2thZ2VzL2FuZ3VsYXJfZGV2a2l0L2J1aWxkX29wdGltaXplci9zcmMvcHVyaWZ5L3dlYnBhY2stcGx1Z2luLnRzIl0sIm5hbWVzIjpbXSwibWFwcGluZ3MiOiI7O0FBU0EscURBQWdEO0FBQ2hELHFDQUE4QztBQU85QyxNQUFhLFlBQVk7SUFDdkIsZ0JBQWdCLENBQUM7SUFDVixLQUFLLENBQUMsUUFBa0I7UUFDN0IsUUFBUSxDQUFDLE1BQU0sQ0FBQyxhQUFhLEVBQUUsQ0FBQyxXQUFvQyxFQUFFLEVBQUU7WUFDdEUsOEVBQThFO1lBQzlFLFdBQVcsQ0FBQyxRQUFRLENBQUMsSUFBSSxDQUFDLDBEQUEwRCxDQUFDLENBQUM7WUFFdEYsV0FBVyxDQUFDLE1BQU0sQ0FBQyx1QkFBdUIsRUFBRSxDQUFDLE1BQWUsRUFBRSxRQUFvQixFQUFFLEVBQUU7Z0JBQ3BGLE1BQU0sQ0FBQyxPQUFPLENBQUMsQ0FBQyxLQUFZLEVBQUUsRUFBRTtvQkFDOUIsS0FBSyxDQUFDLEtBQUs7eUJBQ1IsTUFBTSxDQUFDLENBQUMsUUFBZ0IsRUFBRSxFQUFFLENBQUMsUUFBUSxDQUFDLFFBQVEsQ0FBQyxLQUFLLENBQUMsQ0FBQzt5QkFDdEQsT0FBTyxDQUFDLENBQUMsUUFBZ0IsRUFBRSxFQUFFO3dCQUM1QixNQUFNLE9BQU8sR0FBRywyQkFBa0IsQ0FBQyxXQUFXLENBQUMsTUFBTSxDQUFDLFFBQVEsQ0FBQyxDQUFDLE1BQU0sRUFBRSxDQUFDLENBQUM7d0JBRTFFLElBQUksT0FBTyxDQUFDLE1BQU0sR0FBRyxDQUFDLEVBQUU7NEJBQ3RCLE1BQU0sYUFBYSxHQUFHLElBQUksK0JBQWEsQ0FBQyxXQUFXLENBQUMsTUFBTSxDQUFDLFFBQVEsQ0FBQyxFQUFFLFFBQVEsQ0FBQyxDQUFDOzRCQUNoRixPQUFPLENBQUMsT0FBTyxDQUFDLENBQUMsTUFBTSxFQUFFLEVBQUU7Z0NBQ3pCLGFBQWEsQ0FBQyxNQUFNLENBQUMsTUFBTSxDQUFDLEdBQUcsRUFBRSxNQUFNLENBQUMsT0FBTyxDQUFDLENBQUM7NEJBQ25ELENBQUMsQ0FBQyxDQUFDOzRCQUNILFdBQVcsQ0FBQyxNQUFNLENBQUMsUUFBUSxDQUFDLEdBQUcsYUFBYSxDQUFDO3lCQUM5QztvQkFDSCxDQUFDLENBQUMsQ0FBQztnQkFDUCxDQUFDLENBQUMsQ0FBQztnQkFDSCxRQUFRLEVBQUUsQ0FBQztZQUNiLENBQUMsQ0FBQyxDQUFDO1FBQ0wsQ0FBQyxDQUFDLENBQUM7SUFDTCxDQUFDO0NBQ0Y7QUEzQkQsb0NBMkJDIiwic291cmNlc0NvbnRlbnQiOlsiLyoqXG4gKiBAbGljZW5zZVxuICogQ29weXJpZ2h0IEdvb2dsZSBJbmMuIEFsbCBSaWdodHMgUmVzZXJ2ZWQuXG4gKlxuICogVXNlIG9mIHRoaXMgc291cmNlIGNvZGUgaXMgZ292ZXJuZWQgYnkgYW4gTUlULXN0eWxlIGxpY2Vuc2UgdGhhdCBjYW4gYmVcbiAqIGZvdW5kIGluIHRoZSBMSUNFTlNFIGZpbGUgYXQgaHR0cHM6Ly9hbmd1bGFyLmlvL2xpY2Vuc2VcbiAqL1xuLy8gdHNsaW50OmRpc2FibGUtbmV4dC1saW5lOm5vLWltcGxpY2l0LWRlcGVuZGVuY2llc1xuaW1wb3J0IHsgQ29tcGlsZXIsIGNvbXBpbGF0aW9uIH0gZnJvbSAnd2VicGFjayc7XG5pbXBvcnQgeyBSZXBsYWNlU291cmNlIH0gZnJvbSAnd2VicGFjay1zb3VyY2VzJztcbmltcG9ydCB7IHB1cmlmeVJlcGxhY2VtZW50cyB9IGZyb20gJy4vcHVyaWZ5JztcblxuXG5pbnRlcmZhY2UgQ2h1bmsge1xuICBmaWxlczogc3RyaW5nW107XG59XG5cbmV4cG9ydCBjbGFzcyBQdXJpZnlQbHVnaW4ge1xuICBjb25zdHJ1Y3RvcigpIHsgfVxuICBwdWJsaWMgYXBwbHkoY29tcGlsZXI6IENvbXBpbGVyKTogdm9pZCB7XG4gICAgY29tcGlsZXIucGx1Z2luKCdjb21waWxhdGlvbicsIChjb21waWxhdGlvbjogY29tcGlsYXRpb24uQ29tcGlsYXRpb24pID0+IHtcbiAgICAgIC8vIFdlYnBhY2sgNCBwcm92aWRlcyB0aGUgc2FtZSBmdW5jdGlvbmFsaXR5IGFzIHRoaXMgcGx1Z2luIGFuZCBUUyB0cmFuc2Zvcm1lclxuICAgICAgY29tcGlsYXRpb24ud2FybmluZ3MucHVzaCgnUHVyaWZ5UGx1Z2luIGlzIGRlcHJlY2F0ZWQgYW5kIHdpbGwgYmUgcmVtb3ZlZCBpbiAwLjcuMC4nKTtcblxuICAgICAgY29tcGlsYXRpb24ucGx1Z2luKCdvcHRpbWl6ZS1jaHVuay1hc3NldHMnLCAoY2h1bmtzOiBDaHVua1tdLCBjYWxsYmFjazogKCkgPT4gdm9pZCkgPT4ge1xuICAgICAgICBjaHVua3MuZm9yRWFjaCgoY2h1bms6IENodW5rKSA9PiB7XG4gICAgICAgICAgY2h1bmsuZmlsZXNcbiAgICAgICAgICAgIC5maWx0ZXIoKGZpbGVOYW1lOiBzdHJpbmcpID0+IGZpbGVOYW1lLmVuZHNXaXRoKCcuanMnKSlcbiAgICAgICAgICAgIC5mb3JFYWNoKChmaWxlTmFtZTogc3RyaW5nKSA9PiB7XG4gICAgICAgICAgICAgIGNvbnN0IGluc2VydHMgPSBwdXJpZnlSZXBsYWNlbWVudHMoY29tcGlsYXRpb24uYXNzZXRzW2ZpbGVOYW1lXS5zb3VyY2UoKSk7XG5cbiAgICAgICAgICAgICAgaWYgKGluc2VydHMubGVuZ3RoID4gMCkge1xuICAgICAgICAgICAgICAgIGNvbnN0IHJlcGxhY2VTb3VyY2UgPSBuZXcgUmVwbGFjZVNvdXJjZShjb21waWxhdGlvbi5hc3NldHNbZmlsZU5hbWVdLCBmaWxlTmFtZSk7XG4gICAgICAgICAgICAgICAgaW5zZXJ0cy5mb3JFYWNoKChpbnNlcnQpID0+IHtcbiAgICAgICAgICAgICAgICAgIHJlcGxhY2VTb3VyY2UuaW5zZXJ0KGluc2VydC5wb3MsIGluc2VydC5jb250ZW50KTtcbiAgICAgICAgICAgICAgICB9KTtcbiAgICAgICAgICAgICAgICBjb21waWxhdGlvbi5hc3NldHNbZmlsZU5hbWVdID0gcmVwbGFjZVNvdXJjZTtcbiAgICAgICAgICAgICAgfVxuICAgICAgICAgICAgfSk7XG4gICAgICAgIH0pO1xuICAgICAgICBjYWxsYmFjaygpO1xuICAgICAgfSk7XG4gICAgfSk7XG4gIH1cbn1cbiJdfQ==