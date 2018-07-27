
def call(lintDirectory){
    sh "pylint --output-format=parseable ${lintDirectory} > pylint.log || exit 0"
    sh 'cat pylint.log'

    step([
        $class: 'WarningsPublisher',
        parserConfigurations: [[
        parserName: 'PYLint',
        pattern   : 'pylint.log'
        ]],
        unstableTotalAll: '50',
        usePreviousBuildAsReference: true
    ])
}
