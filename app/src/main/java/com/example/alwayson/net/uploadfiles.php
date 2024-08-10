<?php
$uploadDir = 'uploads/';
if (!file_exists($uploadDir)) {
    mkdir($uploadDir, 0777, true);
}

$response = array();
$uploadSuccess = true;

foreach ($_FILES['files']['name'] as $key => $name) {
    $tmpName = $_FILES['files']['tmp_name'][$key];
    $filePath = $uploadDir . basename($name);

    if (move_uploaded_file($tmpName, $filePath)) {
        $response[] = array(
            'file' => $name,
            'status' => 'uploaded',
            'path' => $filePath
        );
    } else {
        $response[] = array(
            'file' => $name,
            'status' => 'failed'
        );
        $uploadSuccess = false;
    }
}

//if ($uploadSuccess) {
//    http_response_code(200);
//} else {
//    http_response_code(500);
//}

echo json_encode($response);
?>