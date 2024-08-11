<?php

// 设置响应头
header('Content-Type: application/json');

// 获取请求的 JSON 数据
$requestBody = file_get_contents('php://input');
$data = json_decode($requestBody, true);

$response = [];

// 验证数据是否完整
if (isset($data['name']) && isset($data['age']) && isset($data['email'])) {
    $name = $data['name'];
    $age = $data['age'];
    $email = $data['email'];

    // 处理接收到的数据
    // 可以进行数据库插入操作或其他逻辑
    // 假设一切正常，返回成功响应
    $response['success'] = true;
    $response['message'] = 'User created successfully.';
} else {
    $response['success'] = false;
    $response['message'] = 'Invalid input.';
}

// 输出 JSON 响应
echo json_encode($response);

?>