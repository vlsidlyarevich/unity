var gulp = require('gulp');
var concat = require('gulp-concat');
var concatVendor = require('gulp-concat-vendor');
var uglify = require('gulp-uglify');
var minify = require('gulp-minify-css');
var mainBowerFiles = require('main-bower-files');
var inject = require('gulp-inject');
var runSequence = require('gulp-run-sequence');
var gzip = require('gulp-gzip');
var clone = require('gulp-clone');
var series = require('stream-series');
var filter = require('gulp-filter');
var babel = require('gulp-babel');


gulp.task('bower-js-libs', function () {
    gulp.src(mainBowerFiles(), {base: 'bower_components'})
        .pipe(filter(['**/*.js']))
        // .pipe(babel({presets: ['es2015']}))
        // .pipe(concat('lib.min.js'))
        // .pipe(uglify())
        .pipe(gulp.dest('../unity-web/src/main/webapp/provider/js'));
});

gulp.task('bower-css', function () {
    gulp.src(mainBowerFiles(), {base: 'bower_components'})
        .pipe(filter('**/*.css'))
        // .pipe(concat('lib.min.css'))
        // .pipe(minify())
        .pipe(gulp.dest('../unity-web/src/main/webapp/provider/css'));
});

gulp.task('copy-fonts', function () {
    gulp.src(mainBowerFiles('**/dist/fonts/*.{ttf,woff,woff2,eof,svg}'))
        .pipe(gulp.dest('../unity-web/src/main/webapp/provider/fonts/'));
});

// Default Task
gulp.task('default', function () {
    runSequence('bower-js-libs', 'bower-css', 'copy-fonts');
});