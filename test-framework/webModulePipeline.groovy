#!Groovy

pipeline {
    agent any

    tools {
        maven "Apache Maven 3.6.3"
    }

    stages {

        stage("mvn test") {
            steps {
                script {
                    sh """
                        cd test-framework/selenium-web-testing/
                        mvn -Dmaven.test.failure.ignore=true clean test 
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