
def call(lintDirectory){
    sh "pylint --rcfile=.pylintrc ${lintDirectory} > pylint.log || exit 0"
    sh 'cat pylint.log'

    step([
        $class: 'WarningsPublisher',
        parserConfigurations: [[
        parserName: 'PYLint',
        pattern   : 'pylint.log'
        ]],
        unstableTotalAll: '0',
        usePreviousBuildAsReference: true
    ])
}