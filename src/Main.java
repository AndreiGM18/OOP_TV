import java.io.File;
import java.io.IOException;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import fileio.Input;

public final class Main {
    private Main() {
    }

    /**
     *
     * @param args args[0] is the input file's path and args[1] is the output file's path
     */
    public static void main(final String[] args) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        ArrayNode arrayNode = objectMapper.createArrayNode();
        ObjectNode objectNode = objectMapper.createObjectNode();
        objectNode.put("hello", args[0]);
        arrayNode.add(objectNode);
        File result = new File(args[1]);
        File in = new File(args[0]);
        Input input = objectMapper.readValue(in, Input.class);
        System.out.println(input.getActions().get(4).getType());
        ObjectWriter objectWriter = objectMapper.writerWithDefaultPrettyPrinter();
        objectWriter.writeValue(result, arrayNode);
    }
}
