from http.server import BaseHTTPRequestHandler, HTTPServer
import cgi
import requests

from fastapi import FastAPI, File, UploadFile, Form
from typing import List

app = FastAPI()


@app.post("/upload/")
async def upload(text: str = Form(...), files: List[UploadFile] = File(...)):
    file_infos = []
    text
    for file in files:
        file_content = await file.read()
        file_infos.append({
            "filename": file.filename,
            "content_type": file.content_type,
            "size": len(file_content)
        })
        # 这里你可以保存文件或进行进一步处理
        with open(f'/path/to/save/{file.filename}', 'wb') as f:
            f.write(file_content)

    return {"file_infos": file_infos}


class UploadHTTPRequestHandler(BaseHTTPRequestHandler):

    def do_POST(self):
        form = cgi.FieldStorage(
            fp=self.rfile,
            headers=self.headers,
            environ={'REQUEST_METHOD': 'POST'}
        )
        files = []
        text = None
        for item_key in form.keys():
            item = form[item_key]
            item_type = item.type
            if item_type == 'text/plain':  # 文本
                text = item.file.read()
                print(text)
            else:  # 文件
                filename = item.filename
                filedata = item.file.read()
                files.append((item_key, (filename, filedata, item_type)))
                print(filename)
        requests.post('', data={'text', text}, files=files)
        # 返回响应
        self.send_response(200)
        self.end_headers()
        self.wfile.write(b"File uploaded successfully!")


def run(server_class=HTTPServer, handler_class=UploadHTTPRequestHandler, port=8080):
    server_address = ('127.0.0.1', port)
    httpd = server_class(server_address, handler_class)
    print(f'Server running on port {port}...')
    httpd.serve_forever()


if __name__ == "__main__":
    run()
