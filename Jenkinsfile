pipeline {
    agent any

    stages {
        stage('GIT') {
            steps {
                echo 'Recuperation code de Git'
                withCredentials([string(credentialsId: 'GITHUB_TOKEN', variable: 'GIT_TOKEN')]) {
                    git branch: 'foyer', url: "https://houas6:${GIT_TOKEN}@github.com/houas6/tp-foyer.git"
                }
            }
        }
        stage('MAVEN BUILD') {
            steps {
                echo 'Lancement de mvn clean et mvn compile'
                sh 'mvn clean compile'
            }
        }
        stage('JACOCO') {
            steps {
                echo 'Generating JaCoCo code coverage report'
                sh 'mvn test'
                sh 'mvn jacoco:prepare-agent test jacoco:report'
            }
        }
        stage('SONARQUBE') {
            steps {
                echo 'Analyse de code'
                withCredentials([string(credentialsId: 'Sonarqube_ID', variable: 'SONAR_TOKEN')]) {
                    sh 'mvn sonar:sonar -Dsonar.login=$SONAR_TOKEN -Dsonar.coverage.jacoco.xmlReportPaths=target/site/jacoco/jacoco.xml -Pcoverage'
                }
            }
        }
        stage('NEXUS') {
            steps {
                echo 'Deploying artifacts to Nexus repository'
                sh 'mvn deploy'
            }
        }
        stage('DOCKER IMAGE') {
            steps {
                echo 'Building Docker image'
                sh 'docker build -t aziz/tp-foyer:1.0.0 /var/lib/jenkins/workspace/MyFirstPipeline'
                sh 'docker tag aziz/tp-foyer:1.0.0 azyzbc/tp-foyer:latest'
            }
        }
        stage('DOCKER HUB') {
            steps {
                echo 'Pushing Docker image to Docker Hub'
                withCredentials([usernamePassword(credentialsId: 'Dockerhub_ID', usernameVariable: 'DOCKER_USER', passwordVariable: 'DOCKER_PASSWORD')]) {
                    sh '''
                        docker login -u $DOCKER_USER -p $DOCKER_PASSWORD
                        docker push azyzbc/tp-foyer:latest
                    '''
                }
            }
        }
        stage('Docker Image Scan') { 
            steps { 
                sh "trivy image --scanners vuln --format table -o trivy-image-report.html azyzbc/tp-foyer:latest" 
            } 
        }
        stage('DOCKER COMPOSE') {
            steps {
                echo 'Running Docker Compose'
                sh '''
                    docker compose up -d
                    docker compose logs
                '''
            }
        }
        stage('Check Grafana') {
            steps {
                script {
                    def command = "curl -s -o /dev/null -w '%{http_code}' -L  'http://192.***.**.*:3000' "
                    def response = sh(script: command, returnStdout: true).trim()
                    echo "Response: ${response}" // Print the response for debugging

                    if (response == "200") {
                        echo "Grafana is running and accessible"
                    } else {
                        currentBuild.result = 'UNSTABLE'
                        echo "WARNING: Grafana is not running or accessible (HTTP ${response})"
                    }
                }
            }
        }
    } // End of stages

    post { 
        always { 
            script { 
                def jobName = env.JOB_NAME 
                def buildNumber = env.BUILD_NUMBER 
                def pipelineStatus = currentBuild.result ?: 'UNKNOWN' 
                def bannerColor = pipelineStatus.toUpperCase() == 'SUCCESS' ? 'green' : 'red'
                
                def sonarDashboardUrl = "http://192.***.**.*:9000/dashboard?id=tn.esprit%3Atp-foyer"

                def body = """ 
                    <html> 
                    <body> 
                    <div style="border: 4px solid ${bannerColor}; padding: 10px;"> 
                        <h2>${jobName} - Build ${buildNumber}</h2> 
                        <div style="background-color: ${bannerColor}; padding: 10px;"> 
                            <h3 style="color: white;">Pipeline Status: ${pipelineStatus.toUpperCase()}</h3> 
                        </div> 
                        <p>Check the <a href="${env.BUILD_URL}">console output</a>.</p>
                        <p>Check the <a href="${sonarDashboardUrl}">SonarQube report</a> for code analysis details.</p>
                    </div> 
                    </body> 
                    </html> 
                """ 

                emailext ( 
                    subject: "${jobName} - Build ${buildNumber} - ${pipelineStatus.toUpperCase()}", 
                    body: body, 
                    mimeType: 'text/html', // Ensure the body is treated as HTML
                    to: '', 
                    from: '', 
                    replyTo: '', 
                    attachLog: true, // Attach the build log to the email
                    attachmentsPattern: 'trivy-image-report.html' 
                ) 
            } 
        } 
    } 
}
