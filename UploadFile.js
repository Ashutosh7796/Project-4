const express = require('express');
const https = require('https');
const crypto = require('crypto');
const app = express();

app.use(express.json({ limit: '10mb' }));

app.post('/forward-image', async (req, res) => {
  const body = req.body;

  // console.log('imageBytes', { imageBytes, content });
  body.imageName = body.imageName.replace(/\s/g, '_');
  await uploadImage(body);

  res.status(200).send(body.imageName);
});

app.listen(8081, () => {
  console.log('Node.js server is running on port 8081');
});

function generateHttpHeaders(fileLength, contentType, imageName) {
  const SPACE = 'photoupload';
  const REGION = 'nyc3';
  const STORAGETYPE = 'STANDARD';
  const KEY = 'DO00HM9JKCRNT3RT2K6G';
  const SECRET = '+SL/5zTF+ZbQ5iWKp8ZC4j53+mSBo2Kh8U74oJywxlI';
  const spacePath = '/';
  const acl = 'x-amz-acl:public-read';
  const storageType = `x-amz-storage-class:${STORAGETYPE}`;
  const date = new Date().toUTCString();
  const stringToSign = `PUT\n\n${contentType}\n${date}\n${acl}\n${storageType}\n/${SPACE}${spacePath}${imageName}`;
  const signature = crypto.createHmac('sha1', SECRET).update(stringToSign).digest('base64');


  const options = {
    hostname: `${SPACE}.${REGION}.digitaloceanspaces.com`,
    path: `${spacePath}${imageName}`,
    method: 'PUT',
    headers: {
      Host: `${SPACE}.${REGION}.digitaloceanspaces.com`,
      Date: date,
      'Content-Type': contentType,
      'Content-Length': fileLength,
      'x-amz-storage-class': 'STANDARD',
      'x-amz-acl': 'public-read',
      'Authorization': `AWS ${KEY}:${signature}`
    }
  }
  return options;
}

function uploadImage(payloadBody) {
  return new Promise((resolve, reject) => {
    let responseBody = '';
    const imageBuffer = Buffer.from(payloadBody.imageBytes, 'base64');
    const contentLength = Buffer.byteLength(imageBuffer); // Calculate content length

    const req = https.request(generateHttpHeaders(contentLength, payloadBody.contentType, payloadBody.imageName), (res) => {
      res.on('data', (data) => {
        responseBody += data;
      });
      res.on('end', () => {
        console.log('Response Body:', responseBody);
        resolve(true);
      });
    });
    req.on('error', (error) => { reject(error); });
    req.write(imageBuffer);
    req.end();
  });
}






//function uploadImage(payloadBody) {
//  return new Promise((resolve, reject) => {
//    let responseBody = '';
//    const imageBuffer = Buffer.from(payloadBody.imageBytes, 'base64');
//    const req = https.request(generateHttpHeaders(payloadBody.contentLength, payloadBody.contentType, payloadBody.imageName), (res) => {
//      res.on('data', (data) => {
//        responseBody += data;
//      });
//      res.on('end', () => {
//        console.log('Response Body:', responseBody);
//        resolve(true);
//      });
//    });
//    req.on('error', (error) => { reject(error); });
//    req.write(imageBuffer);
//    req.end();
//  });
//
//}

