package es.uned.lsi.PL_ci.controller

import es.uned.lsi.PL_ci.entity.User
import es.uned.lsi.PL_ci.service.UserService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.access.prepost.PostFilter
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

import javax.transaction.Transactional

@RestController
@RequestMapping('users')
@Transactional
@PreAuthorize('isAuthenticated()')
class UserController {
    @Autowired
    UserService userService

    @GetMapping
    @PostFilter('hasRole("Admin") or filterObject.username==principal.username')
    List<User> findAll(){
        userService.findAll()
    }

    @GetMapping('{id}')
    @PreAuthorize('hasRole("Admin") or #id == principal.username')
    User findById(@PathVariable String id){
        userService.findById(id)
    }

    @PostMapping
    @PreAuthorize('hasRole("Admin")')
    User create(@RequestBody User user){
        userService.create(user)
    }

    @PutMapping('{id}/password')
    @PreAuthorize('hasRole("Admin") or #id == principal.username')
    User updatePassword(@PathVariable String id, @RequestBody(required = true) String password){
        userService.updatePassword(id,password)
    }

    @PutMapping('{id}/enabled')
    @PreAuthorize('hasRole("Admin")')
    User updateEnabled(@PathVariable String id, @RequestBody(required = true) boolean enabled){
        userService.updateEnabled(id,enabled)
    }

    @DeleteMapping('{id}')
    @PreAuthorize('hasRole("Admin")')
    User deleteById(@PathVariable String id){
        userService.deleteById(id)
    }
}
