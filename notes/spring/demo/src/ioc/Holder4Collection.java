package ioc;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Holder4Collection {
    protected Object[] m_obj;
    protected String[] m_arr;
    protected List<String> m_list;
    protected Map<String, String> m_map;
    protected Set<String> m_set;

    public Object[] getM_obj() { return m_obj; }
    public void setM_obj(Object[] m_obj) { this.m_obj = m_obj; }
    public String[] getM_arr() { return m_arr; }
    public void setM_arr(String[] m_arr) { this.m_arr = m_arr; }
    public List<String> getM_list() { return m_list; }
    public void setM_list(List<String> m_list) { this.m_list = m_list; }
    public Map<String, String> getM_map() { return m_map; }
    public void setM_map(Map<String, String> m_map) { this.m_map = m_map; }
    public Set<String> getM_set() { return m_set; }
    public void setM_set(Set<String> m_set) { this.m_set = m_set; }

    @Override
    public String toString() {
        return String.format(
                "CollectionHolder {\n\tm_obj  = %s\n\tm_arr  = %s\n\tm_list = %s\n\tm_map  = %s\n\tm_set  = %s\n}",
                Arrays.toString(m_obj),
                Arrays.toString(m_arr),
                m_list, m_map, m_set
        );
    }
}
