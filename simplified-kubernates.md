## Step0 : Install kind
```bash
choco install kind
```
- Navigate to kind folder to create cluster with ingress controller
- 
```bash
./create-cluster.sh
```
- Navigate to k8s folder
```bash
kubectl apply -f ./namespace-book-app.yaml
```

- Run all the config
```bash
kubectl apply -R -f .
```
