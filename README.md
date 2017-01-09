# How to run the example

* Start OpenShift local installation `oc cluster up`.
* Rebuild this project `mvn clean install`.
* Publish artifacts to OpenShift `mvn fabric8:run`.
* Install a service `oc create -f service.yaml`.
* Verify logs `oc logs po/...`. There should be no exception in logs. The first Pod should become a coordinator.
* Scale the pods `oc scale dc jgroups-dns-ping --replicas=5`.
* Verify logs `oc logs po/...`. A new view should be installed.

# Testing SRV Entries

* Switch to `config-test-srv.xml` configuration
* Note that discovery is now performed using SRV entries

# Debugging

* Use RHEL image with diagnosis tools: `oc run rheltest --image=registry.access.redhat.com/rhel7/rhel-tools --restart=Never --attach -i --tty`.
* Use `dig +search jgroups-dns-ping.myproject.svc.cluster.local` and make sure that proper IPs are returned.