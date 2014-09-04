package homework.util;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import org.hibernate.SessionFactory;

/**
 * filter koji presrece zahteve upucene servletima i upravlja Hibernate sesijom
 * primenjuje se na svaki request
 */

/**
 * @author ibranovic
 */

@WebFilter(filterName = "HibernateSessionFilter",urlPatterns = {"/*"})
public class HibernateSessionRequestFilter implements Filter {
    
    private SessionFactory sf;
    
    @Override
     public void init(FilterConfig filterConfig) {
        sf = HibernateUtil.getSessionFactory();
    }
    
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws ServletException {
                try {
                    sf.getCurrentSession().beginTransaction();
                    //pozovi sledeci filter (nastavi obradu zahteva)
                    chain.doFilter(request, response);
                    sf.getCurrentSession().getTransaction().commit();
                } catch (Throwable ex) {
                    if(sf.getCurrentSession().getTransaction().isActive())
                        sf.getCurrentSession().getTransaction().rollback();
                    throw new ServletException(ex);
                }       
    }
    
    @Override
    public void destroy() {
       sf.close(); 
    }
    
}
