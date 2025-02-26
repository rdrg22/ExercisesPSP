# Kubernetes Setup on Docker Desktop
Enable Kubernetes in Docker Desktop

1. Open **Docker Desktop**.
2. Go to **Settings > Kubernetes**.
3. Enable the **"Enable Kubernetes"** option and apply the changes.
4. Wait for Kubernetes to start properly.

## Verify that Kubernetes is Running
Run the following command in the terminal:
```bash
kubectl get nodes
```

##### You should see a node in the **Ready** state.
---
# Create and Containerize Services
#### We need to create the different files that contain the backend code.
Create app.py
```bash
from fastapi import FastAPI
app = FastAPI()
@app.get("/")
def read_root():
    return {"message": "Version 1.0 - Backend Running!"}
```
Create requirements.txt
```bash
fastapi
uvicorn
```
Create DockerFile
```bash
FROM python:3.9-slim
WORKDIR /app
COPY requirements.txt .
RUN pip install --no-cache-dir -r requirements.txt
COPY . .
CMD ["uvicorn", "app:app", "--host", "0.0.0.0", "--port", "8000"]
```
Build and tag the Docker image.
```bash
docker build -t backend:v1 .
```
---
#### We need to create the different files that contain the frontend code.
Create server.js
```bash
const express = require('express');
const axios = require('axios');
const app = express();
app.get('/', async (req, res) => {
    try {
        const response = await axios.get('http://backend-service:8000/');
        res.send(`<h1>${response.data.message}</h1>`);
    } catch (error) {
        res.send(`<h1>Error connecting to backend</h1>`);
    }
});
app.listen(3000, () => console.log('Frontend running on port 3000'));
```
Create package.json
```bash
{
    "name": "frontend",
    "version": "1.0.0",
    "dependencies": {
        "express": "^4.17.1",
        "axios": "^0.21.1"
    }
}
```
Create Dockerfile
```bash
FROM node:14
WORKDIR /app
COPY package.json .
RUN npm install
COPY . .
CMD ["node", "server.js"]
```
Build and tag the Docker image.
```bash
docker build -t frontend:v1 .
```
---
# Deploy Kubernetes
#### We need to create the YAML files that will be the Kubernetes configurations, which we will later deploy.
Create backend-deployment.yaml
```bash
apiVersion: apps/v1
kind: Deployment
metadata:
  name: backend-deployment
spec:
  replicas: 2
  selector:
    matchLabels:
      app: backend
  template:
    metadata:
      labels:
        app: backend
    spec:
      containers:
      - name: backend
        image: backend:v1
        ports:
        - containerPort: 8000
```
Create backend-service.yaml
```bash
apiVersion: v1
kind: Service
metadata:
  name: backend-service
spec:
  selector:
    app: backend
  ports:
    - protocol: TCP
      port: 8000
      targetPort: 8000
  type: ClusterIP
```
Create frontend-deployment.yaml
```bash
apiVersion: apps/v1
kind: Deployment
metadata:
  name: frontend-deployment
spec:
  replicas: 2
  selector:
    matchLabels:
      app: frontend
  template:
    metadata:
      labels:
        app: frontend
    spec:
      containers:
      - name: frontend
        image: frontend:v1
        ports:
        - containerPort: 3000
```
Create frontend-service.yaml
```bash
apiVersion: v1
kind: Service
metadata:
  name: frontend-service
spec:
  selector:
    app: frontend
  ports:
    - protocol: TCP
      port: 3000
      targetPort: 3000
  type: NodePort
```
Now we execute the following commands, and we should see a satisfaction message on the screen 
indicating that it has been successfully deployed.
```bash
kubectl apply -f backend-deployment.yaml
kubectl apply -f backend-service.yaml
kubectl apply -f frontend-deployment.yaml
kubectl apply -f frontend-service.yaml
```
Now, with the following command, we verify that they have been deployed.
```bash
kubectl get pods
```
# Update the backend and apply Rolling Update
#### We need to modify the app.py file for the new version
Old version
```bash
from fastapi import FastAPI
app = FastAPI()
@app.get("/")
def read_root():
    return {"message": "Version 1.0 - Backend Running!"}
```
New version
```bash
@app.get("/")
def read_root():
    return {"message": "Version 2.0 - Backend Updated!"}
```
We have to rebuild the image
```bash
docker build -t backend:v2
```
Update the deploy with rolling update
```bash
kubectl set image deployment/backend-deployemnt backend=backend:v2
```
Let's monitor the new version.
```bash
kubectl rollout status deployment/backend-deployment
```
We have to verify the new version
```bash
kubectl get pods
```
# Rollback
#### If you need to comeback to the old version, do this
```bash
kubectl rollout undo deployment/backend-deployment
```
