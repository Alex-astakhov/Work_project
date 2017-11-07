package dataModels.apiModels.v1;

public class EmptyData implements ApiData {
    @Override
    public boolean validate() {
        System.out.println("\nEmpty data doesn't need validation!\n");
        return false;
    }

    @Override
    public boolean validate(boolean additionalCondition) {
        System.out.println("\nEmpty data doesn't need validation!\n");
        return false;
    }
}
