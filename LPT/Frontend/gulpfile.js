var gulp = require('gulp');

gulp.task('default', function() {
    // place code for your default task here
});

gulp.task('copy:libs', ['clean'], function() {
    return gulp.src([
        'node_modules/core-js/client/shim.min.js',
        <script src="node_modules/zone.js/dist/zone.js"></script>
        <script src="node_modules/reflect-metadata/Reflect.js"></script>
        <script src="node_modules/systemjs/dist/system.src.js"></script>

        'node_modules/angular2/bundles/angular2-polyfills.js',
        'node_modules/systemjs/dist/system.src.js',
        'node_modules/rxjs/bundles/Rx.js',
        'node_modules/angular2/bundles/angular2.dev.js',
        'node_modules/angular2/bundles/router.dev.js'
    ])
        .pipe(gulp.dest('dist/lib'))
});


<script src=""></script>

