# ![](https://raw.githubusercontent.com/JabirC/AdventChess/frontend/AdventChess/src/assests/AdventChess-logo.png)

<br>

## :bulb: About

The main objective of this **cloud-native** project is to represent the state-of-the-art of a **distributed**, **reliable**, and **highly scalable** system by interpreting the most relevant principles of [**Reactive
Domain Driven Design**](https://www.infoq.com/articles/modeling-uncertainty-reactive-ddd/).

> Domain-Driven Design can aid with managing uncertainty through the use of good modeling.  
> -- Vaughn Vernon

**Scalability** and **Resilience** require **low coupling** and **high cohesion**, principles strongly linked to the proper understanding of the business through **well-defined boundaries**, combined with a healthy and
efficient integration strategy such as **Event-driven Architecture** (EDA).

The [**Event Storming**](https://www.eventstorming.com/) workshop provides a practical approach to **subdomain decomposition**, using **Pivotal Events** to correlate business capabilities across **Bounded Contexts** while promoting **reactive
integration** between Aggregates.

The **reactive** integration between **Bounded Contexts** configures an **Event-driven Architecture** (EDA) where **Commands** are acknowledged and sent to the Bus by the Web API (BFF/Gateway) while **Events** are
broadcasted to the **Query** side and/or other Aggregates.

**Independence**, as the main characteristic of a **Microservice**, can only be found in a **Bounded Context**.

The [**Event Sourcing**](https://www.eventstore.com/event-sourcing) is a proprietary implementation that, in addition to being **naturally auditable** and **data-driven**, represents the most efficient persistence mechanism ever. An **eventual state
transition** Aggregate design is essential at this point. The **Event Store** comprises EF Core (ORM) + MSSQL (Database).

> State transitions are an important part of our problem space and should be modeled within our domain.  
> -- Greg Young

[**Projections**](https://www.eventstore.com/event-sourcing#Projections) are **asynchronously denormalized** and stored on a NoSQL Database(MongoDB); Nested documents should be avoided here; Each projection has its index and **fits perfectly into a view or component**,
mitigating unnecessary data traffic and making the reading side as efficient as possible.

The splitting between **Command** and **Query** stacks occurs logically through the [**CQRS**](https://cqrs.files.wordpress.com/2010/11/cqrs_documents.pdf) pattern and fiscally via a **Microservices** architecture. Each stack is an individual deployable unit with its database,
and the data flows from Command to Query stack via **Domain** and/or **Summary** events.

As a **domain-centric** approach, [**Clean Architecture**](https://blog.cleancoder.com/uncle-bob/2012/08/13/the-clean-architecture.html) provides the appropriate isolation between the **Core** (Application + Domain) and "times many" **Infrastructure** concerns.

proach that focuses on creating software unrelated to any specific domain, application, or technology. Some strategies adopted in this project are related to particular principles, such as the **domain-centric** to support the business (**Reactive DDD** and **Clean Architecture**); **Event Sourcing** + **object-relational mapping** (ORM) for persistence mechanism; **Containers** for immutable environments; and **Kubernetes** (K8s) for cloud deployment.

The **domain-centric** approach is a design pattern that separates the core business logic of an application from other concerns, such as the user interface or infrastructure, making it easier to maintain and extend the application, as the core domain is isolated from other system components. Additionally, the ability to move the core domain to different applications while **keeping the essence of the business** can be helpful for organizations that need to support multiple applications or platforms.

A key aspect of agnostic obsession is **event-sourcing** and **object-relational mapping** (ORM) as a persistence mechanism. Event sourcing involves storing the history of events occurring within a system rather than the system's current state, **avoiding advanced database capabilities such as Joins, Triggers, Procedures, and more**. On the other hand, ORM is a technique that maps objects in a program to data stored in a database, making it easier and more abstract to manage data.

Another essential aspect of agnostic obsession is using **containers to create immutable environments**. Containers are a lightweight virtualization form that allows packaging an application and its dependencies into a single, self-contained unit, making it easy to deploy and run the application in any environment without worrying about the underlying infrastructure or platform.

Finally, agnostic obsession often involves using **Kubernetes** (K8s) for cloud deployment. K8s is an open-source platform for managing and deploying containerized applications. It is widely used in the industry and supported by most cloud providers, making it a natural choice for agnostic obsession.

In summary, agnostic obsession is a powerful approach that allows for creating highly flexible and adaptable software that can be easily moved and deployed in different environments. Using event-sourcing, ORM, Containers, and K8s, it is possible to build resilient, scalable, and easy-to-maintain systems.

</details>

## License

This project is licensed under the MIT License - see the [LICENSE](./LICENSE) file for details
