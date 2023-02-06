find . -name "*.properties" | grep -v log4j.properties | while read line; do
  cat $line | \
      sed "s/username=\'[^\']*\'/username=\'<KAFKA_API_KEY>\'/" | \
      sed "s/password=\'[^\']*\'/password=\'<KAFKA_API_SECRET>\'/" | \
      sed 's/bootstrap.servers=.*$/bootstrap.servers=<BOOTSTRAP_SERVERS>:9092/' \
      > $line.template
done
