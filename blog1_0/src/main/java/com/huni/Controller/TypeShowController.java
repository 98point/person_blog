package com.huni.Controller;

import com.huni.entity.Type;
import com.huni.service.BlogService;
import com.huni.service.TypeService;
import com.huni.vo.BlogQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Controller
public class TypeShowController {
    @Autowired
    private TypeService typeService;
    @Autowired
    private BlogService blogService;
    @GetMapping("/type/{id}")
    public String type(Model model, @PageableDefault(sort={"updateTime"},direction = Sort.Direction.DESC ) Pageable pageable,@PathVariable Long id){
        List<Type> types=  typeService.listTypeTop(100);
        if(id == -1){
            id = types.get(0).getId();
        }
        BlogQuery blogQuery = new BlogQuery();
        blogQuery.setTypeId(id);
        model.addAttribute("types",types);
        model.addAttribute("page",blogService.selectBlog(pageable,blogQuery));
        model.addAttribute("activeTypeId",id);
        return "type";
    }
}