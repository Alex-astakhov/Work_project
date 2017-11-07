package dataModels.apiModels.v1.errorModels.errorBlocks;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import dataModels.apiModels.v1.errorModels.ErrorData;

import java.util.List;

public class ErrorDataWithBlocks<T extends ErrorParams> extends ErrorData {
    @SerializedName("errorBlocks")
    @Expose
    public List<ErrorBlock<T>> errorBlocks = null;

    public class ErrorBlock<T> {
        @SerializedName("notValidBlock")
        @Expose
        public Integer notValidBlock;
        @SerializedName("dataParams")
        @Expose
        public T dataParams;
    }
}
