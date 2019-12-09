package ru.mirea.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Controller
public class ItemController {

    @Autowired
    private BackJDBCTemplate backJDBCTemplate;

    @RequestMapping(value = "", method = RequestMethod.GET)
    @ResponseBody
    public List<Item> get_items() {
        return backJDBCTemplate.geItems();
    }

    @RequestMapping(value = "{id}", method = RequestMethod.DELETE)
    @ResponseBody
    public void delete_item(@PathVariable int id ) {
        backJDBCTemplate.deleteItem(id);
    }

    @RequestMapping(value = "", method = RequestMethod.PUT)
    @ResponseBody
    public ResponseEntity<?> put_item(@RequestBody Map<String, ?> pet){
        if(pet.containsKey("name")
                && pet.containsKey("type")
                && pet.containsKey("count")
                && pet.containsKey("price")
                && pet.get("name") instanceof String
                && pet.get("type") instanceof String
                && pet.get("count") instanceof Integer) {
            double price;
            if(pet.get("price") instanceof Integer)
                price = (Integer) pet.get("price");
            else
                price = (Double) pet.get("price");
            backJDBCTemplate.putItem(
                    (String) pet.get("name"),
                    (String) pet.get("type"),
                    (Integer) pet.get("count"),
                    price);
            return ResponseEntity.ok().build();
        }
        else return ResponseEntity.badRequest().build();
    }

//    @PostMapping("/api")
//    public ResponseEntity<Resp> doSomething(@RequestBody ReqBody reqBody){
//        Resp resp = null;
//        switch (reqBody.getMethod()){
//            case "get":{
//                resp = new Resp(reqBody.getMethod(), true, backJDBCTemplate.geItems());
//                break;
//            }
//            case "delete":{
//                resp = new Resp(reqBody.getMethod(), true, backJDBCTemplate.deleteItem(reqBody.getPost().getId()));
//                break;
//            }
//            default:{
//                break;
//            }
//        }
//        System.out.println(resp.getMethod()+"   "+resp.getItem());
//        return new ResponseEntity<Resp>(resp, HttpStatus.OK);
//    }
}
