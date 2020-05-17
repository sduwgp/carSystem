package com.jkxy.car.api.controller;

import com.jkxy.car.api.pojo.Car;
import com.jkxy.car.api.service.CarService;
import com.jkxy.car.api.utils.JSONResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.awt.print.Pageable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * @author wgp
 */
@RestController
@RequestMapping("car")
public class CarController {
    @Autowired
    private CarService carService;

    /**
     * 查询所有
     *
     * @return
     */
    @GetMapping("findAll")
    public JSONResult findAll() {
        List<Car> cars = carService.findAll();
        return JSONResult.ok(cars);
    }

    /**
     * 通过id查询
     *
     * @param id
     * @return
     */
    @GetMapping("findById/{id}")
    public JSONResult findById(@PathVariable int id) {
        Car car = carService.findById(id);
        return JSONResult.ok(car);
    }

    /**
     * 通过车名查询
     *
     * @param carName
     * @return
     */
    @GetMapping("findByCarName/{carName}")
    public JSONResult findByCarName(@PathVariable String carName) {
        List<Car> cars = carService.findByCarName(carName);
        return JSONResult.ok(cars);
    }

    @PostMapping("findCar")
    public JSONResult findCar(@RequestBody Map map)  {
        try {
             String carName = "%";
             int page = 0;
             int size = 0;


            if (map.get("carName") != null && !"".equals(map.get("carName").toString().trim())) {
                carName ="%"+map.get("carName").toString().trim()+"%";
            }

            if (map.get("page") != null && !"".equals(map.get("page").toString().trim())) {
                page = Integer.valueOf(map.get("page").toString().trim());
            }

            if (map.get("size") != null && !"".equals(map.get("size").toString().trim())) {
                size =Integer.valueOf(map.get("size").toString().trim());
            }
            page = (page-1)*size;

            List<Car> carList = carService.findCar(carName,page,size);
            return JSONResult.ok(carList);
        }catch (Exception e){
            return JSONResult.errorException(e.getMessage());
        }
    }

    /**
     * 通过id删除
     *
     * @param id
     * @return
     */
    @GetMapping("deleteById/{id}")
    public JSONResult deleteById(@PathVariable int id) {
        carService.deleteById(id);
        return JSONResult.ok();
    }

    /**
     * 通过id更新全部信息
     *
     * @return
     */
    @PostMapping("updateById")
    public JSONResult updateById(Car car) {
        carService.updateById(car);
        return JSONResult.ok();
    }

    /**
     * 通过id增加
     *
     * @param car
     * @return
     */
    @PostMapping("insertCar")
    public JSONResult insertCar(Car car) {
        carService.insertCar(car);
        return JSONResult.ok();
    }
}
