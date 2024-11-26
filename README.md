# Food Delivery System

A Event Driven Architecture based food delivery system using Flask, Spring Boot, and Kafka.

## Services

1. **Consumer Service (Flask)**
   - REST API for food orders
   - Publishes to Kafka topic
   ```bash
   cd consumer-service
   python -m venv venv
   pip install -r requirements.txt
   python app.py
   ```

2. **Order Processor Service (Java)**
   - Routes orders to delivery partners
   ```bash
   cd order-processor-service
   mvn clean install
   mvn spring-boot:run
   ```

3. **Delivery Partner Service (Python)**
   - Processes delivery orders
   ```bash
   cd delivery-partner-service
   python -m venv venv
   pip install kafka-python
   python delivery_partner_service.py
   ```

## Prerequisites
- Python 3.8+
- Java 17
- Apache Kafka



## Configuration

Update the respective config files for each service:

### Consumer Service (Flask)
```python
# config.py
KAFKA_CONFIG = {
    'bootstrap_servers': 'localhost:9092',
    'topic': 'order-topic'
}
```

### Order Processor (Spring)
```properties
# application.properties
application.kafka.bootstrap-servers=localhost:9092
application.kafka.order-topic=order-topic
```

### Delivery Partner
```json
{
    "kafka": {
        "bootstrap_servers": "localhost:9092",
        "topic": "deliveryPartner1"
    }
}
```

## Quick Start
1. Start Kafka
2. Start Consumer Service
3. Start Order Processor
4. Start Delivery Partner Service
