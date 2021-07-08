package pojo;

import lombok.Getter;
import lombok.Setter;
import org.junit.Test;

import java.util.List;
import java.util.Map;

@Getter
@Setter

public class PokePojo {

    private int count;
    private String next;
    private  String previous;
    private List <Map<String, String>> results;


}

