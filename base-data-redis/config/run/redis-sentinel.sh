cd /opt/redis
echo "123456" | sudo redis1-3.2.8/src/redis-sentinel /opt/redis/run/sentinel26379.conf
echo "123456" | sudo redis2-3.2.8/src/redis-sentinel /opt/redis/run/sentinel26380.conf
echo "123456" | sudo redis3-3.2.8/src/redis-sentinel /opt/redis/run/sentinel26381.conf
