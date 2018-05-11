# ms-base-common
---------
# Doc
---------

## 基础服务使用

- Mpper层

```java
    public interface ElementMapper extends Mapper<Element> {}
```

- Biz层

```java
    public class ElementBiz extends BaseBiz<ElementMapper,Element> {}
```

## 业务服务使用

- Mapper层

```java
    public interface AgentTrustorMapper extends BizMapper<AgentTrustor> {
        
    }
```

- Biz层

```java
    public class AgentTrustorBiz extends BusinessBiz<AgentTrustorMapper,AgentTrustor> {}
```

- Entity层

```java
    public class AgentTrustor implements Serializable,UniqueVerifiableVO {}
```