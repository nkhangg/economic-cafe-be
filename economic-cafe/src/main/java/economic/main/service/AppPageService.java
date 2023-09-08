package economic.main.service;

import economic.main.payload.respone.ApiResponce;
import jakarta.servlet.http.HttpServletRequest;

public interface AppPageService {
    public ApiResponce homePage(HttpServletRequest request);
}
