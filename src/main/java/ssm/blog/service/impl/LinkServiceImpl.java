package ssm.blog.service.impl;

import org.springframework.stereotype.Service;
import ssm.blog.dao.LinkDao;
import ssm.blog.entity.Link;
import ssm.blog.service.LinkService;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * Created by liuxiwen on 2017/1/16.
 */
@Service("linkService")
public class LinkServiceImpl implements LinkService {

    @Resource
    private LinkDao linkDao;

    @Override
    public List<Link> getLinkData() {
        return linkDao.getLinkData();
    }

    @Override
    public List<Link> listLinkData(Map<String, Object> map) {
        return linkDao.listLinkData(map);
    }

    @Override
    public Long getTotal(Map<String, Object> map) {
        return linkDao.getTotal(map);
    }

    @Override
    public Integer addLink(Link link) {
        return linkDao.addLink(link);
    }

    @Override
    public Integer updateLink(Link link) {
        return linkDao.updateLink(link);
    }

    @Override
    public Integer deleteLink(Integer id) {
        return linkDao.deleteLink(id);
    }
}
