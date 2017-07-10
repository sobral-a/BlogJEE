package interceptor;

import javax.interceptor.InterceptorBinding;
import java.lang.annotation.*;

/**
 * Created by alexa on 16/06/2017.
 */

@Retention(RetentionPolicy.RUNTIME)
@Target( { ElementType.TYPE, ElementType.METHOD })
@InterceptorBinding
@Inherited
public @interface Transaction
{


}
