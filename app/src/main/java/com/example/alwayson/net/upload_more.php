<?php
// 设置上传目录
$uploadDir = 'uploads/';
$response = [
    'status' => 'Invalid request.'
];
// 检查是否通过 POST 方法上传文件
try{
    if ($_SERVER['REQUEST_METHOD'] == 'POST') {
        // 检查是否上传了文件
        if (isset($_FILES['files'])) {
            // 允许的文件类型
            $allowedFileExtensions = ['html', 'mp4', 'conf', 'ini', 'pdf', 'docx', 'xlsx'];
//            // 遍历每个上传的文件
//            for ($i = 0; $i < count($_FILES['files']['name']); $i++) {
//                // 检查文件上传是否存在错误
//                if ($_FILES['files']['error'][$i] === UPLOAD_ERR_OK) {
//                    // 获取文件信息
//                    $fileTmpPath = $_FILES['files']['tmp_name'][$i];
//                    $fileName = $_FILES['files']['name'][$i];
//                    $fileSize = $_FILES['files']['size'][$i];
//                    $fileType = $_FILES['files']['type'][$i];
//                    $fileNameCmps = explode(".", $fileName);
//                    $fileExtension = strtolower(end($fileNameCmps));
//
//                    // 设置新文件名，防止文件名冲突
//                    $newFileName = md5(time() . $fileName) . '.' . $fileExtension;
//
//                    if (in_array($fileExtension, $allowedFileExtensions)) {
//                        // 确保上传目录存在
//                        if (!is_dir($uploadDir)) {
//                            mkdir($uploadDir, 0777, true);
//                        }
//
//                        // 构建目标文件路径
//                        $destPath = $uploadDir . $newFileName;
//
//                        // 移动文件到目标目录
//                        if (move_uploaded_file($fileTmpPath, $destPath)) {
//                            $response = [
//                                'status' => "File $fileName is successfully uploaded.<br>"
//                            ];
//                        } else {
//                            $response = [
//                                'status' => "Error moving the file $fileName.<br>"
//                            ];
//                        }
//                    } else {
//                        $response = [
//                            'status' => "Upload failed for $fileName. Allowed file types: " . implode(',', $allowedFileExtensions) . "<br>"
//                        ];
//                    }
//                } else {
//                    $response = [
//                        'status' => "Error occurred for file $fileName. Error code: " . $_FILES['files']['error'][$i] . "<br>"
//                    ];
//                }
//            }
            $response = [
                'status' => 'success'.count($_FILES['files']),
            ];
        } else {
            $response = [
                'status' => 'No files were uploaded.'.implode(array_keys($_FILES)),
                'message' => 'No file uploaded or there was an upload error'
            ];
        }
    }
}catch (Exception $e){
    $response = [
        'status' => 'Caught exception: '
    ];
}
echo json_encode($response);