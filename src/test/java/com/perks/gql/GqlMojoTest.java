package com.perks.gql;

import org.junit.jupiter.api.Test;

class GqlMojoTest {

    @Test
    public void shouldGenerateModelFromSchema() throws Exception {

        // prepare
        GqlMojo mojo = new GqlMojo();

        // act
        mojo.execute();
    }

}