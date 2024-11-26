from kafka import KafkaProducer
import json
import random

class KafkaOrderProducer:
    def __init__(self, bootstrap_servers):
        self.producer = KafkaProducer(
            bootstrap_servers=bootstrap_servers,
            key_serializer=lambda k: str(k).encode('utf-8'),  # Serialize key as bytes
            value_serializer=lambda v: json.dumps(v).encode('utf-8')  # Serialize value as JSON
        )

    def publish_order(self, topic, order_data):
        try:
            random_key = random.randint(1000000000, 9999999999)

            self.producer.send(topic, key=random_key, value=order_data)
            self.producer.flush()

            return {
                "status": "success",
                "message": f"Order published to Kafka with key {random_key}"
            }
        except Exception as e:
            return {"status": "error", "message": str(e)}
