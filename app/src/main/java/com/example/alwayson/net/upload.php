<?php
// 设置响应头为 JSON
header("Content-Type: application/json");

// 设置上传目录
$uploadDir = 'uploads/';

// 确保上传目录存在
if (!is_dir($uploadDir)) {
    mkdir($uploadDir, 0777, true);
}

// 检查是否通过 POST 方法上传文件
if ($_SERVER['REQUEST_METHOD'] === 'POST') {
    // 检查文件上传是否存在错误
    if (isset($_FILES['file']) && $_FILES['file']['error'] === UPLOAD_ERR_OK) {
        // 获取文件信息
        $fileTmpPath = $_FILES['file']['tmp_name'];
        $fileName = $_FILES['file']['name'];
        $fileSize = $_FILES['file']['size'];
        $fileType = $_FILES['file']['type'];
        $fileNameCmps = explode(".", $fileName);
        $fileExtension = strtolower(end($fileNameCmps));

        $des = isset($_POST['description']) ? $_POST['description'] : null;

        // 设置新文件名，防止文件名冲突
        $newFileName = md5(time() . $fileName) . '.' . $fileExtension;

        // 允许的文件类型
        $allowedFileExtensions = ['jpg', 'jpeg', 'png', 'gif', 'pdf', 'docx', 'xlsx', 'conf', 'mp4', 'html'];

        // 验证文件类型
        if (in_array($fileExtension, $allowedFileExtensions)) {
            // 构建目标文件路径
            $destPath = $uploadDir . $newFileName;

            // 移动文件到目标目录
            if (move_uploaded_file($fileTmpPath, $destPath)) {
                $response = [
                    'status' => 'success',
                    'message' => 'File uploaded successfully hhh',
                    'file_name' => $des,
                    'file_size' => $fileSize,
                    'file_type' => $fileType,
                ];
            } else {
                $response = [
                    'status' => 'error',
                    'message' => 'Error moving the uploaded file'
                ];
            }
        } else {
            $response = [
                'status' => 'error',
                'message' => 'Upload failed. Allowed file types: ' . implode(',', $allowedFileExtensions)
            ];
        }
    } else {
        $response = [
            'status' => 'error',
            'message' => 'No file uploaded or there was an upload error'
        ];
    }
} else {
    $response = [
        'status' => 'error',
        'message' => 'Invalid request method'
    ];
}

// 输出 JSON 响应
echo json_encode($response);