# Spring Filter

This article discusses the dependencies, classes, and features of a project utilizing the Spring Filter of turkraft library for dynamic filtering of entities in a Spring API.

# Dependencies

The **`pom.xml`** file contains the Maven dependencies required for `springfilter`

```xml
<dependency>
    <groupId>com.turkraft.springfilter</groupId>
    <artifactId>jpa</artifactId>
    <version>3.1.2</version>
</dependency>
```

- See repo at :https://github.com/turkraft/springfilter

# **ListHumanController**

The **`ListHumanController`** class is a Spring **`RestController`** that handles HTTP requests related to humans. It is responsible for retrieving a list of humans based on provided filtering specifications.

This specification is going to be handled by the `@Filter` annotation of `turkraft/springfilter`. so we can achieve dynamic filter for the entity `Human`

```java
@RestController
@RequiredArgsConstructor
@RequestMapping("/humans")
public class ListHumanController {

    private final HumanService humanService;

    @GetMapping
    public List<Human> getHumans(@Filter Specification<Human> spec) {
        return humanService.findAll(spec);
    }

}
```

1. **`@Filter`**: indicates that the method parameter should be resolved as a filtering specification. This means that the method accepts a specification that defines the criteria for filtering the list of humans returned by the **`getHumans`** endpoint.
2. **`Specification<T>`**:  is a generic type representing a specification in the Spring Data JPA framework. It is used in combination with the **`findAll`** method in the **`HumanService`** class to perform filtering on the **`Human`** entity.

Here, the **`@Filter`** annotation indicates that the **`Specification<Human> spec`** parameter should be resolved as a filtering specification, enabling flexible querying based on the specified conditions.

## **Human**

The **`Human`** class represents a human entity and contains information such as the ID, name, and age.

```java
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Human {

    @Id
    private String id;
    private String name;
    private int age;
}

```

- The **`@Entity`** annotation indicates that this class is an entity and can be mapped to a database table.

## **HumanRepository**

The **`HumanRepository`** interface extends the **`JpaRepository`** and **`JpaSpecificationExecutor`** interfaces, providing database access methods for the **`Human`** entity.

```java
@Repository
public interface HumanRepository extends JpaRepository<Human, String>, JpaSpecificationExecutor<Human> {
```

The **`HumanRepository`** interface extends **`JpaSpecificationExecutor<Human>`**. This means that **`HumanRepository`** inherits the methods defined in **`JpaSpecificationExecutor`**, allowing it to execute specifications on the **`Human`** entity.

The **`JpaSpecificationExecutor`** interface provides methods such as:

- **`findAll(Specification<T> spec)`**: Retrieves a list of entities that match the given specification.
- **`findOne(Specification<T> spec)`**: Retrieves a single entity that matches the given specification.

## **data.sql**

The **`data.sql`** file contains SQL statements that populate the **`human`** table in the database with initial data.

```sql
insert into human (id, name, age) values (1, 'Pablo', 31);
insert into human (id, name, age) values (2, 'George', 30);
```

- The SQL statements insert two rows into the **`human`** table, specifying the **`id`**, **`name`**, and **`age`** values.

## Results:

- Filtering humans with name = Pablo
    - GET [http://localhost:8080/humans?filter=name:'Pablo'](http://localhost:8080/humans?filter=name:%27Pablo%27)
    - Response Body: `[{"id":"1","name":"Pablo","age":31}]`
- Filtering humans with id > 0
    - GET [http://localhost:8080/humans?filter=id>0](http://localhost:8080/humans?filter=id%3E0)
    - Response Body: `[{"id":"1","name":"Pablo","age":31},{"id":"2","name":"George","age":30}]`

For value comparators, check the following syntax:

https://github.com/turkraft/springfilter#value-comparators