$baseUrl = "http://localhost:8080/api"
$outputFile = "api_test_results.json"
$headers = @{
    "Content-Type" = "application/json"
}


$endpoints = @(
# Users
    @{ name="CreateTeacher"; method="POST"; url="/users/create"; data='{"username":"jdoe","firstName":"John","lastName":"Doe","email":"john.doe@example.com","password":"1234","role":"TEACHER","active":true}' },
    @{ name="CreateStudent"; method="POST"; url="/users/create"; data='{"username":"sstudent","firstName":"Sarah","lastName":"Student","email":"sarah.student@example.com","password":"pass","role":"STUDENT","active":true}' },
    @{ name="GetAllUsers"; method="GET"; url="/users/all"; data=$null },

    # Courses
    @{ name="CreateCourse"; method="POST"; url="/courses/create"; data='{"title": "Math 101"}' },
    @{ name="GetAllCourses"; method="GET"; url="/courses/all"; data=$null },

    # Exams (nécessite un teacher_id et un course_id)
    @{ name="GetAllExams"; method="GET"; url="/exams/all"; data=$null },

    # Questions (nécessite un examId)
    @{ name="GetAllQuestions"; method="GET"; url="/questions/all"; data=$null },

    # Quizzes
    @{ name="CreateQuiz"; method="POST"; url="/quizzes/create"; data='{"title": "Math Quiz"}' },
    @{ name="AssociateQuestionsToQuiz"; method="PUT"; url="/quizzes/1/associate-questions"; data='[1]' },
    @{ name="GetAllQuizzes"; method="GET"; url="/quizzes/all"; data=$null }
)


$results = @()


function Log-Info {
    param ([string]$message)
    Write-Host "[INFO] $message" -ForegroundColor Cyan
}

function Log-Success {
    param ([string]$message)
    Write-Host "[SUCCESS] $message" -ForegroundColor Green
}

function Log-Error {
    param ([string]$message)
    Write-Host "[ERROR] $message" -ForegroundColor Red
}


function Test-Api {
    param (
        [string]$name,
        [string]$method,
        [string]$url,
        [string]$data
    )

    $fullUrl = "$baseUrl$url"
    Log-Info "Testing $name ($method $fullUrl)..."


    $body = $null
    if ($data -ne $null -and $data -ne "") {
        $body = $data
    }

    try {
        if ($method -eq "GET" -or $method -eq "DELETE") {
            $response = Invoke-RestMethod -Uri $fullUrl -Method $method -Headers $headers -ErrorAction Stop
        } else {
            $response = Invoke-RestMethod -Uri $fullUrl -Method $method -Headers $headers -Body $body -ContentType "application/json" -ErrorAction Stop
        }

        Log-Success "$name - Success"
        $status = "Success"
    }
    catch {
        Log-Error "$name - Failed: $($_.Exception.Message)"
        $response = @{ error = $_.Exception.Message }
        $status = "Failed"
    }


    $results += @{
        "name" = $name
        "method" = $method
        "url" = $fullUrl
        "status" = $status
        "response" = $response
    }
}


foreach ($endpoint in $endpoints) {
    Test-Api -name $endpoint.name -method $endpoint.method -url $endpoint.url -data $endpoint.data
}


$results | ConvertTo-Json -Depth 10 | Out-File -Encoding utf8 $outputFile
Write-Host "[INFO] Résultats sauvegardés dans $outputFile" -ForegroundColor Cyan
