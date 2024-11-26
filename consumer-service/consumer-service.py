from flask import Flask, request, jsonify
from producer import KafkaOrderProducer
from config import get_config

app = Flask(__name__)

# Load configuration
config = get_config()

# Initialize Kafka Producer
producer = KafkaOrderProducer(config.KAFKA_BOOTSTRAP_SERVERS)

@app.route('/place-order', methods=['POST'])
def place_order():
    try:
        # Parse order details from the request
        order_data = request.json
        if not order_data:
            return jsonify({"error": "Invalid input, JSON body required"}), 400

        # Publish order to Kafka
        response = producer.publish_order(config.ORDER_TOPIC, order_data)
        return jsonify(response), 200 if response["status"] == "success" else 500
    except Exception as e:
        return jsonify({"status": "error", "message": str(e)}), 500

if __name__ == '__main__':
    app.run(debug=True, port=5000)
