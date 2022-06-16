basepath=$(pwd)
for port in $(seq 1 3); \
do \
mkdir -p ./kafka-cluster-${port}

sudo chown -R docker-root:docker-root ${basepath}/kafka-cluster-${port}
done
