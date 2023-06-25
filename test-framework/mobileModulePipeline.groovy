#!Groovy

pipeline {
    agent any

    tools {
        maven "Apache Maven 3.6.3"
    }

    environment {
        APPIUM_PORT = 4723
    }

    parameters {
        choice(name: 'platform', choices: ['Android', 'iOS'], description: 'Platform:')
        text(name: 'deviceName', defaultValue: 'RFCR30E35RL', description: 'Device name:')
        text(name: 'packageName', defaultValue: 'com.sec.android.app.popupcalculator', description: 'Package:')
        text(name: 'activityName', defaultValue: '.Calculator', description: 'Activity:')
    }

    stages {

        stage('Appium Server start') {
            steps {
                echo "Starting APPIUM server"
                sh "/c/Users/aiordan/AppData/Roaming/npm/appium --port ${APPIUM_PORT} &"
            }
        }

        stage("Executing tests") {
            steps {
                script {
                    sh """
                        cd /C/Users/aiordan/IdeaProjects/master/SeleniumTestFramework/test-framework/appium-mobile-testing
                        mvn -Dmaven.test.failure.ignore=true clean test -Dplatform=$platform -DdeviceName=$deviceName -DpackageName=$packageName -Dactivity=$activityName
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
                            results          : [[path: 'test-framework/appium-mobile-testing/target/allure-results']]
                    ])
                }
            }
        }

        stage('Appium Server stop') {
            steps {
                echo "Stop appium server"
                sh """
                    APPIUM_PID=\$(netstat -ano -p tcp  | awk '/:4723 */ {split(\$NF,a,"/"); print a[2],a[1]}')
                    echo "\$APPIUM_PID" > pid.txt
                    """

                bat """
                set /p pid=<pid.txt
                tskill %pid%
                """
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