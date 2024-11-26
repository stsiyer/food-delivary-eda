# delivery_partner_service.py
from kafka import KafkaConsumer
import json
import logging
import sys
import os

# Configure logging
logging.basicConfig(
    level=logging.INFO,
    format='%(asctime)s - %(message)s'
)
logger = logging.getLogger(__name__)

def load_config():
    try:
        config_path = os.path.join(os.path.dirname(__file__), 'config.json')
        with open(config_path, 'r') as file:
            return json.load(file)
    except FileNotFoundError:
        logger.error("config.json not found!")
        sys.exit(1)
    except json.JSONDecodeError as e:
        logger.error(f"Invalid JSON in config file: {str(e)}")
        sys.exit(1)
    except Exception as e:
        logger.error(f"Error loading config: {str(e)}")
        sys.exit(1)

def start_delivery_service(config):
    # Create Kafka consumer from config
    consumer = KafkaConsumer(
        config['kafka']['topic'],
        bootstrap_servers=config['kafka']['bootstrap_servers'],
        group_id=config['kafka']['group_id'],
        auto_offset_reset=config['kafka']['auto_offset_reset'],
        value_deserializer=lambda x: json.loads(x.decode('utf-8'))
    )
    
    logger.info(f"{config['delivery_partner']['name']} Started - Listening to {config['kafka']['topic']}")
    
    try:
        for message in consumer:
            order = message.value
            
            # Print delivery confirmation
            print(f"\n=== {config['delivery_partner']['name']} - Order Delivered! ===")
            print(f"Region: {config['delivery_partner']['region']}")
            print(f"Order ID: {order['order_id']}")
            print(f"Customer: {order['customer_name']}")
            print(f"Address: {order['delivery_address']}")
            print(f"Items:")
            for item in order['items']:
                print(f"  - {item['quantity']}x {item['item_name']}")
            print(f"Total Amount: ${order['total_amount']:.2f}")
            print("=====================\n")
            
    except KeyboardInterrupt:
        print("\nShutting down...")
    finally:
        consumer.close()

if __name__ == "__main__":
    config = load_config()
    start_delivery_service(config)
