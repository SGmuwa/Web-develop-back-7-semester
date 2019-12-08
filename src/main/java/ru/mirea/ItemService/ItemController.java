package ru.mirea.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class ItemController {

    @Autowired
    private BackJDBCTemplate backJDBCTemplate;

    @RequestMapping(value = "get_items", method = RequestMethod.GET)
    @ResponseBody
    public List<Item> get_items() {
        return backJDBCTemplate.geItems();
    }

    @RequestMapping(value = "delete_item/{id}", method = RequestMethod.DELETE)
    @ResponseBody
    public List<Item> delete_item(@PathVariable int id ) {
        return backJDBCTemplate.deleteItem(id);
    }

    @RequestMapping(value = "update_item/{name}/{type}/{count}/{price}", method = RequestMethod.GET)
    @ResponseBody
    public void update_item(@PathVariable String name, @PathVariable String type, @PathVariable int count, @PathVariable double price){
        backJDBCTemplate.updateItem(name, type, count, price);
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