#!Groovy

pipeline {
    agent any

    tools {
        maven "Apache Maven 3.6.3"
    }

    parameters {
        choice(name: 'browserType', choices: ['Chrome', 'Firefox', 'Edge'], description: 'Browser name:')
    }

    stages {

        stage("mvn test") {
            steps {
                script {
//                    For WINDOWS WORKERS
//                    bat "\"C:\\Program Files\\Git\\bin\\sh.exe\" -c \"cd test-framework/selenium-web-testing/ &&" +
//                            " mvn -Dmaven.test.failure.ignore=true clean test -Dbrowser=$browserType\""

//                    for LINUX
                    sh """
                         cd /C/Users/aiordan/IdeaProjects/master/SeleniumTestFramework/test-framework/selenium-web-testing/
                        mvn -Dmaven.test.failure.ignore=true clean test -Dbrowser=$browserType
                        """
                }
            }
        }

        stage('Allure report') {
            steps {
                script {
                    allure([
                            includeProperties: false,
                            jdk              : '',
                            properties       : [],
                            reportBuildPolicy: 'ALWAYS',
                            results          : [[path: 'test-framework/selenium-web-testing/target/allure-results']]
                    ])
                }
            }
        }
    }

    post {
        always {
            echo 'One way or another, I have finished !'
        }
        success {
            script {
                echo "SUCCESSFUL"
            }
        }
        unstable {
            echo "UNSTABLE"
        }
        failure {
            script {
                echo "FAILED"
            }
        }
    }

}