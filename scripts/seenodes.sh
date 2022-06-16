docker ps -a | grep kafka
#查找ip
for i in `seq 1 3`;
do
	echo -n "$(docker inspect --format '{{ (index .NetworkSettings.Networks "myzkcomposeprj_brzk-kafka").IPAddress }}' "kafka-cluster-$i") ";
done

