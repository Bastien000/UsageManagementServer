package com.viennapoint.viennapointserver.service.getService;

import java.util.List;
import java.util.Map;

public interface GetService {

    List<String> getBuildingsName();

    Map<Integer , String> getBuildingsNameAndID();

    List<String> getTenantsName();

    Map<Integer , String> getTenantsNameAndID();

    List<Integer> getBuildingsID();
}
