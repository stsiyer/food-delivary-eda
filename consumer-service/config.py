import os

class Config:
    KAFKA_BOOTSTRAP_SERVERS = os.getenv('KAFKA_BOOTSTRAP_SERVERS', 'localhost:9092')
    ORDER_TOPIC = os.getenv('ORDER_TOPIC', 'order')

# Use a function to fetch configuration dynamically (e.g., for testing/staging environments)
def get_config():
    return Config()
