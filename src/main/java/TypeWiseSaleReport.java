import java.util.Map;

public interface TypeWiseSaleReport extends GetId {
    Map<Class, Double> getTypeWiseSales();
}
