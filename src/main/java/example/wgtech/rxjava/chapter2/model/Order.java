package example.wgtech.rxjava.chapter2.models;

/**
 * ObservableFromIterable 연동 Order class-model
 */
public class Order {
    private String mId;

    public Order(String mId) {
        this.mId = mId;
    }

    public String getmId() {
        return mId;
    }

    public void setmId(String mId) {
        this.mId = mId;
    }

    @Override
    public String toString() {
        return new StringBuilder().append("Order ID : ").append(mId).toString();
    }
}
