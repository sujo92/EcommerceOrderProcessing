# EcommerceOrderProcessing

## Components:
### Order Service
RESTful clients that work with single order object, e.g.
i. get order by id
ii. create an order
iii. cancel an order
These clients are customer-facing: web apps, mobile apps, bots etc. So, your
service must support these clients via RESTful APIs.

### BulkOrder Service
i. create orders in bulk/batch,
ii. update order status in bulk/batch.
These clients are the internal services: POS Terminals, fulfilment systems, warehouse picking systems etc.

## Design Decisions:

- BulkOrder sevice accepts bulk data and calls order service to process them.

- To improve performance and scalability of bulk order processor and to deal with connectivity issue between two service Kafka was introduced between them.

- sleuth(adds traceId and spanId) and zipkin was used for distributed tracing.

- ELK was used for centralized the logging.