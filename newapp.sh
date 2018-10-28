v="3.3.0-incubating"
mvn -B archetype:generate \
 -DarchetypeGroupId=org.apache.apex \
 -DarchetypeArtifactId=apex-app-archetype \
 -DarchetypeVersion="$v" \
 -DgroupId=com.example \
 -Dpackage=com.example.myapexapp \
 -DartifactId=myapexapp \
 -Dversion=1.0=SNAPSHOT
