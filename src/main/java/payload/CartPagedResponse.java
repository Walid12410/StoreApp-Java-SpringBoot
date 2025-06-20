package payload;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CartPagedResponse<P> {
    private List<P> data;
    private int page;
    private int size;
    private long totalElements;
    private int totalPages;
    private boolean last;
}
