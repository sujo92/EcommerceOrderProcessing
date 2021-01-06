# EcommerceOrderProcessing

## Components:
### Order Service
RESTful clients that work with single order object, e.g.
- get order by id
- create an order
- cancel an order
These clients are customer-facing: web apps, mobile apps, bots etc. So, your
service must support these clients via RESTful APIs.

### BulkOrder Service
- create orders in bulk/batch,
- update order status in bulk/batch.
These clients are the internal services: POS Terminals, fulfilment systems, warehouse picking systems etc.

## Design Decisions:

- BulkOrder sevice accepts bulk data and calls order service to process them.

- To improve performance and scalability of bulk order processor and to deal with connectivity issue between two service Kafka was introduced between them.

- sleuth(adds traceId and spanId) and zipkin was used for distributed tracing.
