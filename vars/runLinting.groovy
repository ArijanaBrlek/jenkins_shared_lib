
def call(lintDirectories, lintLogFile){
    sh "pylint --output-format=parseable hello_world >> ${lintLogFile}_pylint.log || exit 0"
    sh "cat ${lintLogFile}_pylint.log"

    step([
        $class: 'WarningsPublisher',
        parserConfigurations: [[
            parserName: 'PYLint',
            pattern   : '*_pylint.log'
        ]],
        healthy: '0',
        canResolveRelativePaths: true,
        usePreviousBuildAsReference: true
    ])
}